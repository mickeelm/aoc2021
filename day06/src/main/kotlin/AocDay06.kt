import java.io.File
import java.util.*

fun main() {
    val input = File("input.txt").readLines()[0]
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun solutionPart1(input: String): Long = input.split(',').map { it.toInt() }.let { fishSimulation(it, 80) }

fun solutionPart2(input: String): Long = input.split(',').map { it.toInt() }.let { fishSimulation(it, 256) }

private fun fishSimulation(initialPopulation: List<Int>, daysToModel: Int) =
    MutableList(9) { 0L }.let { dayCounts ->
        initialPopulation.forEach { dayCounts[it]++ }
        repeat(daysToModel) {
            Collections.rotate(dayCounts, -1)
            dayCounts[6] += dayCounts[8]
        }
        dayCounts.sum()
    }