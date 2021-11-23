import java.io.File
import java.util.*

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

private fun List<Int>.indexedNonPrimes(): Set<IndexedNonPrime> =
    primes(this).let {
        mapIndexedNotNull { index, number -> if (!it.contains(number)) IndexedNonPrime(number, index) else null }
            .toSet()
    }

private fun List<Int>.indexedPrimes(): Set<IndexedPrime> =
    primes(this).let {
        mapIndexedNotNull { index, number -> if (it.contains(number)) IndexedPrime(number, index) else null }
            .toSet()
    }

private fun primes(integers: List<Int>): Set<Int> {
    val maxValue = integers.maxOf { it }
    val primeMap = (2..maxValue).associateWith { true }.toSortedMap()

    return detectPrimesRecursive(primeMap, -1)
}

private fun detectPrimesRecursive(primeMap: SortedMap<Int, Boolean>, lastPrime: Int): Set<Int> =
    primeMap.firstNotNullOfOrNull { if (it.value && it.key > lastPrime) it.key else null }?.let { nextPrime ->
        val maxValue = primeMap.lastKey()
        (nextPrime..maxValue step nextPrime).drop(1).forEach { primeMap[it] = false }
        detectPrimesRecursive(primeMap, nextPrime)

    } ?: primeMap.filter { it.value }.keys