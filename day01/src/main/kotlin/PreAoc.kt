import java.io.File

fun main() {
    val input = File("input.txt").readLines().map { it.toInt() }
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun solutionPart1(input: List<Int>) = input.indexedPrimes().sumOf { it.value }

fun solutionPart2(input: List<Int>) = input.indexedNonPrimes().sumOf { it.value }

private class IndexedPrime(number: Int, index: Int) {
    val value = number * index
}

private class IndexedNonPrime(number: Int, index: Int) {
    val value = if (index % 2 == 0) number else -number
}

private fun List<Int>.indexedPrimes(): Set<IndexedPrime> =
    primes(this).let {
        mapIndexedNotNull { index, number -> if (it.contains(number)) IndexedPrime(number, index) else null }
            .toSet()
    }

private fun List<Int>.indexedNonPrimes(): Set<IndexedNonPrime> =
    subtract(primes(this)).let {
        mapIndexedNotNull { index, number -> if (it.contains(number)) IndexedNonPrime(number, index) else null }
            .toSet()
    }

private fun primes(integers: List<Int>): Set<Int> {
    val maxValue = integers.maxOf { it }
    val primeFlags = MutableList(maxValue - 1) { true }

    return detectPrimesRecursive(primeFlags)
}

private fun detectPrimesRecursive(primeFlags: MutableList<Boolean>, lastPrime: Int? = null): Set<Int> {
    val offset = lastPrime?.minus(1) ?: 0
    val nextPrime = primeFlags.drop(offset).indexOfFirst { it }.let { if (it >= 0) it + offset + 2 else null }
    return nextPrime?.run {
        val maxNumber = primeFlags.size + 1
        (nextPrime..maxNumber step nextPrime).drop(1).forEach { primeFlags[it - 2] = false }
        return detectPrimesRecursive(primeFlags, nextPrime)
    } ?: primeFlags.mapIndexedNotNull { index, flag -> if (flag) index + 2 else null }.toSet()
}