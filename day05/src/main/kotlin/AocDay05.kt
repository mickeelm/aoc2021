import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

data class Coordinates(val x: Int, val y: Int)

fun solutionPart1(input: List<String>) = solve(input) { it.first.x == it.second.x || it.first.y == it.second.y }

fun solutionPart2(input: List<String>) = solve(input) {
    it.first.x == it.second.x || it.first.y == it.second.y ||
            abs(it.first.x - it.second.x) == abs(it.first.y - it.second.y)
}

fun solve(input: List<String>, ventCoordinatesFilter: (Pair<Coordinates, Coordinates>) -> Boolean) =
    input.map { it.ventCoordinates() }
        .filter { ventCoordinatesFilter.invoke(it) }
        .flatMap { it.asVentLine() }
        .groupingBy { it }.eachCount()
        .filterValues { it > 1 }.count()

private fun Pair<Coordinates, Coordinates>.asVentLine(): List<Coordinates> {
    val xRange = when (first.x.compareTo(second.x)) {
        -1 -> first.x..second.x
        1 -> first.x downTo second.x
        else -> List(abs(first.y - second.y) + 1) { first.x }
    }

    val yRange = when (first.y.compareTo(second.y)) {
        -1 -> first.y..second.y
        1 -> first.y downTo second.y
        else -> List(abs(first.x - second.x) + 1) { first.y }
    }

    return xRange.zip(yRange).map { Coordinates(it.first, it.second) }
}

private fun String.ventCoordinates(): Pair<Coordinates, Coordinates> =
    split(' ').slice(listOf(0, 2)).flatMap { it.split(',') }.map { it.toInt() }.chunked(2)
        .map { Coordinates(it[0], it[1]) }.let { Pair(it[0], it[1]) }
