import java.io.File
import java.util.*

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

class RiskNode(val x: Int, val y: Int, val totalRisk: Int) : Comparable<RiskNode> {
    override fun compareTo(other: RiskNode): Int {
        val riskComparison = this.totalRisk.compareTo(other.totalRisk)
        if (riskComparison != 0) return riskComparison
        val xComparison = this.x.compareTo(other.x)
        if (xComparison != 0) return xComparison
        return this.y.compareTo(other.y)
    }
}

data class Risks(val risks: List<List<Int>>, val scale: Int = 1)

operator fun Risks.get(x: Int, y: Int) =
    risks[x % risks.size][y % risks.size].rotate(x / risks.size + y / risks.size)

fun Int.rotate(factor: Int): Int {
    val rotated = this + factor
    return if (rotated <= 9) rotated else rotated - 9
}

fun solutionPart1(input: List<String>): Int =
    solve(Risks(input.map { string -> string.map { it.toString().toInt() } }))

fun solutionPart2(input: List<String>): Int =
    solve(Risks(input.map { string -> string.map { it.toString().toInt() } }, 5))

fun solve(risks: Risks): Int {
    val length = risks.risks.size * risks.scale
    val max = length - 1

    val visited = (1..length).map { (1..length).map { false }.toMutableList() }
    var current = RiskNode(0, 0, 0)
    val totalRisks = sortedSetOf(current)

    while (!visited[max][max]) {
        current = totalRisks.first()
        if (current.x > 0) {
            val x = current.x - 1
            if (!visited[x][current.y]) calculateTotalRisk(x, current.y, current.totalRisk, totalRisks, risks)
        }
        if (current.y > 0) {
            val y = current.y - 1
            if (!visited[current.x][y]) calculateTotalRisk(current.x, y, current.totalRisk, totalRisks, risks)
        }
        if (current.x < max) {
            val x = current.x + 1
            if (!visited[x][current.y]) calculateTotalRisk(x, current.y, current.totalRisk, totalRisks, risks)
        }
        if (current.y < max) {
            val y = current.y + 1
            if (!visited[current.x][y]) calculateTotalRisk(current.x, y, current.totalRisk, totalRisks, risks)

        }
        totalRisks.remove(current)
        visited[current.x][current.y] = true
    }
    return current.totalRisk
}

private fun calculateTotalRisk(x: Int, y: Int, incomingTotalRisk: Int, totalRisks: SortedSet<RiskNode>, risks: Risks) {
    val currentNeighborTotalRisk = totalRisks.firstOrNull { it.x == x && it.y == y }
    val potentialNewTotalRiskValue = incomingTotalRisk + risks[x, y]
    if (currentNeighborTotalRisk == null) {
        totalRisks.add(RiskNode(x, y, potentialNewTotalRiskValue))
    } else if (potentialNewTotalRiskValue < currentNeighborTotalRisk.totalRisk) {
        totalRisks.remove(currentNeighborTotalRisk)
        totalRisks.add(RiskNode(x, y, potentialNewTotalRiskValue))
    }
}