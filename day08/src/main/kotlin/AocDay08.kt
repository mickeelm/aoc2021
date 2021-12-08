import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun solutionPart1(input: List<String>) = input.map { entry ->
    entry.split(" | ")[1].split(' ').count { it.length in listOf(2, 3, 4, 7) }
}.sum()

fun solutionPart2(input: List<String>) = input.sumOf { solveDigits(it) }

fun solveDigits(inputRow: String): Int {
    val split = inputRow.split(" | ")
    val unsolved = split[0].split(' ').map { it.toSet() }.toMutableSet()
    val digits = split[1].split(' ').map { it.toSet() }

    val one = unsolved.removeAndGet { it.size == 2 }
    val four = unsolved.removeAndGet { it.size == 4 }
    val seven = unsolved.removeAndGet { it.size == 3 }
    val eight = unsolved.removeAndGet { it.size == 7 }
    val nine = unsolved.removeAndGet { it.size == 6 && it.containsAll(four) }
    val zero = unsolved.removeAndGet { it.size == 6 && it.containsAll(one) }
    val six = unsolved.removeAndGet { it.size == 6 }
    val three = unsolved.removeAndGet { it.containsAll(one) }
    val lowerLeft = eight.minus(nine).first()
    val two = unsolved.removeAndGet { it.contains(lowerLeft) }
    val five = unsolved.first()

    val solved = listOf(zero, one, two, three, four, five, six, seven, eight, nine)
        .mapIndexed { index, set -> set to index.toString() }.toMap()

    return digits.map { solved[it] }.joinToString("").toInt()
}

private fun MutableSet<Set<Char>>.removeAndGet(predicate: (Set<Char>) -> Boolean): Set<Char> {
    val element = first { predicate.invoke(it) }
    remove(element)
    return element
}