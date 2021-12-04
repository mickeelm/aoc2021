import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun solutionPart1(input: List<String>): Int {
    val drawnNumbers = input.first().split(',').map { it.toInt() }

    return input.drop(2).chunked(6) { BingoBoard(drawnNumbers, it) }.minByOrNull { it.numberOfDraws }?.score
        ?: throw RuntimeException("No boards")
}

fun solutionPart2(input: List<String>): Int {
    val drawnNumbers = input.first().split(',').map { it.toInt() }

    return input.drop(2).chunked(6) { BingoBoard(drawnNumbers, it) }.maxByOrNull { it.numberOfDraws }?.score
        ?: throw RuntimeException("No boards")
}

class BingoBoard(private val drawnNumbers: List<Int>, rawBoard: List<String>) {
    private val flattenedBoard = rawBoard.joinToString(" ").trim().split(Regex("\\s+")).map { it.toInt() }
    private val rows = (0..4).map { flattenedBoard.slice(it*5..it*5+4).toMutableSet() } +
            (0..4).map { flattenedBoard.slice(it..it+20 step 5).toMutableSet() }
    val score: Int
    val numberOfDraws: Int

    init {
        var lastNumber: Int? = null
        var draws = 0

        for (number in drawnNumbers) {
            draws++
            lastNumber = number
            rows.forEach { it.remove(number) }
            if(rows.any { it.isEmpty() }) break
        }

        val unmarkedSum = rows.reduce { merged, unmarked -> merged.apply { addAll(unmarked) } }.sum()
        score = lastNumber?.times(unmarkedSum) ?: throw RuntimeException("lastNumber was null")
        numberOfDraws = draws
    }
}