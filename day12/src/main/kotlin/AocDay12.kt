import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun List<String>.caveConnections() =
    flatMap { connection -> connection.split('-').zip(connection.split('-').reversed()) }
        .groupBy { it.first }
        .mapValues { entry -> entry.value.map { it.second }.filter { it != "start" }.toSet() }

fun solutionPart1(input: List<String>) = input.caveConnections().countPaths("start")
fun solutionPart2(input: List<String>) = input.caveConnections().countPaths("start", smallTwice = true)

fun Map<String, Set<String>>.countPaths(
    cave: String,
    smallVisited: Set<String> = emptySet(),
    smallTwice: Boolean = false
): Int {
    if (cave == "end") return 1
    val smallVisitLimitReached = !smallTwice || cave in smallVisited
    val eligiblePaths = getValue(cave).toMutableSet().also { if (smallVisitLimitReached) it.removeAll(smallVisited) }
    if (eligiblePaths.isEmpty()) return 0

    if (cave == cave.lowercase()) {
        return eligiblePaths.sumOf { countPaths(it, smallVisited.plus(cave), !smallVisitLimitReached) }
    }

    return eligiblePaths.sumOf { countPaths(it, smallVisited, !smallVisitLimitReached) }
}