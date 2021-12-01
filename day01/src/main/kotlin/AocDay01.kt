import java.io.File

fun main() {
    val input = File("input.txt").readLines().map { it.toInt() }
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun solutionPart1(input: List<Int>) = input.countIncreases()
fun solutionPart2(input: List<Int>) = input.windowed(size = 3, transform = { window -> window.sum() }).countIncreases()

private fun List<Int>.countIncreases() = fold(IncreaseCounter.START) { counter, next -> counter.evaluate(next) }.count

private class IncreaseCounter(val count: Int, val last: Int) {
    companion object {
        val START = IncreaseCounter(-1, Int.MIN_VALUE)
    }

    fun evaluate(next: Int) = if (last < next) increase(next) else pass(next)

    private fun pass(next: Int) = IncreaseCounter(count, next)
    private fun increase(next: Int) = IncreaseCounter(count + 1, next)
}

