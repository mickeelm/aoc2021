import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

data class Coordinates(val row: Int, val col: Int)

fun solutionPart1(input: List<String>) = lowPoints(input.grid()).values.sum()

fun solutionPart2(input: List<String>) = input.grid().let { grid ->
    lowPoints(grid).map { basinSize(it.key, grid) }.sortedDescending().take(3).reduce(Int::times)
}

private fun List<String>.grid() = map { line -> line.map(Character::getNumericValue) }

private fun lowPoints(numbers: List<List<Int>>): Map<Coordinates, Int> {
    val rows = numbers.size
    val cols = numbers[0].size

    val lowPoints = mutableMapOf<Coordinates, Int>()
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            val number = numbers[row][col]
            if (number == 9) continue
            if (row > 0 && numbers[row - 1][col] <= number) continue
            if (col > 0 && numbers[row][col - 1] <= number) continue
            if (row < rows - 1 && numbers[row + 1][col] <= number) continue
            if (col < cols - 1 && numbers[row][col + 1] <= number) continue
            lowPoints[Coordinates(row, col)] = number + 1
        }
    }
    return lowPoints.toMap()
}

private fun basinSize(lowPoint: Coordinates, grid: List<List<Int>>): Int {
    val rows = grid.size
    val cols = grid[0].size

    val inBasin = mutableSetOf(lowPoint)
    val adjacentNotExplored = lowPoint.adjacent(rows, cols).toMutableSet()
    val ruledOut = mutableSetOf<Coordinates>()

    while (adjacentNotExplored.isNotEmpty()) {
        val adjacent = adjacentNotExplored.removeFirst()
        if (adjacent in ruledOut || adjacent in inBasin) continue
        if (grid[adjacent.row][adjacent.col] == 9) {
            ruledOut.add(adjacent)
        } else {
            inBasin.add(adjacent)
            adjacentNotExplored.addAll(adjacent.adjacent(rows, cols))
        }
    }

    return inBasin.size
}

private fun Coordinates.adjacent(rows: Int, cols: Int): Set<Coordinates> {
    val adjacent = mutableSetOf<Coordinates>()
    if (row > 0) adjacent.add(Coordinates(row - 1, col))
    if (col > 0) adjacent.add(Coordinates(row, col - 1))
    if (row < rows - 1) adjacent.add(Coordinates(row + 1, col))
    if (col < cols - 1) adjacent.add(Coordinates(row, col + 1))

    return adjacent.toSet()
}

private fun MutableSet<Coordinates>.removeFirst() = first().also { remove(it) }