import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

data class Input(val polymer: Polymer, val insertionRules: InsertionRules)
data class CharCount(val char: Char, val count: Long)
data class PairCount(val pair: String, val count: Long)
data class Polymer(val pairCounts: List<PairCount>, val lastChar: Char)
typealias InsertionRules = Map<String, Set<String>>

fun List<String>.parse() =
    filterNot { it.isBlank() }.let { Input(it.first().polymer(), it.subList(1, size - 1).insertionRules()) }

fun String.polymer() = zipWithNext { a, b -> "$a$b" }.groupBy { it }
    .map { PairCount(it.key, it.value.count().toLong()) }.let { Polymer(it, this.last()) }

fun List<String>.insertionRules() =
    associate { rule -> rule.split(" -> ").let { it[0] to setOf(it[0][0] + it[1], it[1] + it[0][1]) } }

fun solutionPart1(input: List<String>) = input.parse().solve(10)
fun solutionPart2(input: List<String>) = input.parse().solve(40)

fun Input.solve(steps: Int) = (1..steps).fold(polymer) { pmr, _ -> pmr.step(insertionRules) }.answer()

fun Polymer.step(insertionRules: InsertionRules) =
    pairCounts.flatMap { pairCount -> insertionRules.getValue(pairCount.pair).map { PairCount(it, pairCount.count) } }
        .groupBy { it.pair }.map { entry -> PairCount(entry.key, entry.value.sumOf { it.count }) }
        .let { Polymer(it, lastChar) }

fun Polymer.answer() = pairCounts.map { CharCount(it.pair.first(), it.count) }.groupBy { it.char }
    .mapValues { cc -> cc.value.sumOf { it.count }.let { if (cc.key == lastChar) it + 1 else it } }.values.sorted()
    .let { it.last() - it.first() }