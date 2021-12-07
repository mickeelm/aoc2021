import java.io.File
import kotlin.math.*

fun main() {
    val input = File("input.txt").readLines()[0]
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun solutionPart1(input: String) = input.split(',').map { it.toInt() }.let { input ->
    val median = input.median()
    input.sumOf { abs(median - it) }
}

fun solutionPart2(input: String) = input.split(',').map { it.toInt() }.let { input ->
    val floorMean = input.floorMean()
    val ceilMean = input.ceilMean()
    min(input.sumOf { (1..abs(floorMean - it)).sum() }, input.sumOf { (1..abs(ceilMean - it)).sum() })
}

fun List<Int>.median() = sorted()[size / 2]
fun List<Int>.floorMean() = floor(sum().toFloat() / size.toFloat()).roundToInt()
fun List<Int>.ceilMean() = ceil(sum().toFloat() / size.toFloat()).roundToInt()