import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

val openers = setOf('(', '[', '{', '<')
val closers = setOf(')', ']', '}', '>')
val pairs = openers.zip(closers).toMap()
val syntaxErrorScores = closers.zip(listOf(3, 57, 1197, 25137)).toMap()
val autoCompleteScores = closers.zip(1L..4L).toMap()

fun solutionPart1(input: List<String>) = input.sumOfNotNull { syntaxErrorScore(it) }
fun solutionPart2(input: List<String>) = input.medianOfNotNull { autoCompleteScore(it) }

fun syntaxErrorScore(line: String): Int? =
    ArrayDeque<Char>().run {
        line.forEach { char ->
            if (char in openers) add(char) else removeLast()
                .also { if (char != pairs[it]) return syntaxErrorScores[char] }
        }
        null
    }

fun autoCompleteScore(line: String): Long? =
    ArrayDeque<Char>().apply {
        line.forEach { char ->
            if (char in openers) add(char) else removeLast()
                .also { if (char != pairs[it]) return null }
        }
    }.asReversed()
        .map { autoCompleteScores.getValue(pairs.getValue(it)) }
        .reduce { score, next -> score * 5 + next }

private fun List<String>.sumOfNotNull(selector: (String) -> Int?) = mapNotNull(selector).sum()
private fun List<String>.medianOfNotNull(selector: (String) -> Long?) =
    mapNotNull(selector).sorted().let { it[it.size / 2] }