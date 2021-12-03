import PositionWeigher.Companion.START
import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun solutionPart1(input: List<String>) = input.fold(START) { weigher, binaryString -> weigher.add(binaryString) }
    .powerConsumption()

fun solutionPart2(input: List<String>): Int {
    val oxygen =
        recursiveRatingValue(input.toSet(), 0) { zeroes, ones -> if (zeroes.size <= ones.size) ones else zeroes }
    val co2 =
        recursiveRatingValue(input.toSet(), 0) { zeroes, ones -> if (zeroes.size <= ones.size) zeroes else ones }

    return oxygen * co2
}

fun recursiveRatingValue(input: Set<String>, position: Int, evaluator: (Set<String>, Set<String>) -> Set<String>): Int {
    if (input.size == 1) {
        return Integer.parseInt(input.first(), 2)
    }

    val zeroes = input.filter { it[position] == '0' }.toSet()
    val ones = input - zeroes
    val remaining = evaluator.invoke(zeroes, ones)

    return recursiveRatingValue(remaining, position + 1, evaluator)
}

class PositionWeigher private constructor(private val weights: List<Int>) {
    companion object {
        val START = PositionWeigher(emptyList())
    }

    fun add(binaryString: String): PositionWeigher {
        val weightsToAdd = binaryString.map { if (it == '1') 1 else -1 }
        if (weights.isEmpty()) {
            return PositionWeigher(weightsToAdd)
        }

        return PositionWeigher(weights.mapIndexed { index, i -> i + weightsToAdd[index] })
    }

    private fun gamma() = weights.map { if (it > 0) '1' else '0' }.joinToString("")
    private fun epsilon() = weights.map { if (it < 0) '1' else '0' }.joinToString("")
    fun powerConsumption() = Integer.parseInt(gamma(), 2) * Integer.parseInt(epsilon(), 2)
}

