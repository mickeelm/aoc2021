import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    when (System.getenv("part")) {
        "part2" -> solutionPart2(input).run { forEach { println(it) } }
        else -> solutionPart1(input).run { println(this) }
    }
}

data class Coordinate(val x: Int, val y: Int)
data class Instruction(val direction: Char, val line: Int)

fun parse(input: List<String>) = input.filter { it.isNotBlank() }.partition { it.startsWith("fold") }.let {
    it.second.coordinates() to it.first.instructions()
}

fun List<String>.coordinates() = map { Coordinate(it.split(',')[0].toInt(), it.split(',')[1].toInt()) }.toSet()

fun List<String>.instructions() =
    map { Instruction(it.split('=')[0].last(), it.split('=')[1].toInt()) }


fun solutionPart1(input: List<String>) = parse(input).let { solve(it.first, listOf(it.second.first())) }.count()
fun solutionPart2(input: List<String>) = parse(input).let { solve(it.first, it.second) }.print()

fun solve(coordinates: Set<Coordinate>, instructions: List<Instruction>) =
    instructions.fold(coordinates) { coord, instr -> coord.foldPaper(instr) }

fun Set<Coordinate>.foldPaper(instruction: Instruction) =
    if (instruction.direction == 'x') verticalFold(instruction) else horizontalFold(instruction)

fun Set<Coordinate>.horizontalFold(instruction: Instruction) =
    filterNot { it.y == instruction.line }
        .map { if (it.y > instruction.line) Coordinate(it.x, instruction.line - (it.y - instruction.line)) else it }
        .toSet()

fun Set<Coordinate>.verticalFold(instruction: Instruction) =
    filterNot { it.x == instruction.line }
        .map { if (it.x > instruction.line) Coordinate(instruction.line - (it.x - instruction.line), it.y) else it }
        .toSet()

fun Set<Coordinate>.print() =
    (0..this.maxOf { it.y }).map { y ->
        (0..this.maxOf { it.x }).joinToString("") { x -> if (Coordinate(x, y) in this) "███" else "   " }
    }