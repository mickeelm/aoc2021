import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun solutionPart1(input: List<String>) = input.initialize().run { (1..100).sumOf { step() } }
fun solutionPart2(input: List<String>) =
    input.initialize().run { generateSequence(1) { it + 1 }.first { step() == 100 } }

data class Coordinates(val row: Int, val col: Int)
typealias Grid = MutableMap<Coordinates, Int>

fun emptyGrid() = (0..9).flatMap { row -> (0..9).map { Coordinates(row, it) } }.associateWith { 0 }.toMutableMap()

fun List<String>.initialize() = emptyGrid().also {
    forEachIndexed { row, raw ->
        raw.map(Character::getNumericValue).forEachIndexed { col, level -> it[Coordinates(row, col)] = level }
    }
}

fun Grid.step(): Int {
    increaseAll()
    var nextToFlash = flashReady()
    val flashed = mutableSetOf<Coordinates>()
    while (nextToFlash.isNotEmpty()) {
        flash(nextToFlash)
        flashed.addAll(nextToFlash)
        nextToFlash = flashReady() - flashed
    }
    reset(flashed)
    return flashed.size
}

fun Grid.reset(flashed: Set<Coordinates>) = filterKeys { it in flashed }.forEach { put(it.key, 0) }

fun Grid.flash(flashCoordinates: Set<Coordinates>) =
    flashCoordinates.forEach {
        (it.row-1..it.row+1).forEach { row -> (it.col-1..it.col+1).forEach { col -> increase(Coordinates(row,col)) } }
    }

fun Grid.increase(coordinates: Coordinates) = get(coordinates)?.let { put(coordinates, it + 1) }
fun Grid.increaseAll() = forEach { increase(it.key) }
fun Grid.flashReady() = filterValues { it > 9 }.keys
