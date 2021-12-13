import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    when (System.getenv("part")) {
        "part2" -> solutionPart2(input).run { forEach { println(it) } }
        else -> solutionPart1(input).run { println(this) }
    }
}

data class Instruction(val direction: Char, val line: Int)

fun solutionPart1(input: List<String>) =
    parse(input).let { solve(it.second.coordinates(), listOf(it.first.first()).instructions()) }.count()

fun solutionPart2(input: List<String>) =
    parse(input).let { solve(it.second.coordinates(), it.first.instructions()) }.print()

fun parse(input: List<String>) = input.filter { it.isNotBlank() }.partition { it.startsWith("fold") }

fun solve(coordinates: Set<Pair<Int, Int>>, instructions: List<Instruction>) =
        instructions.fold(coordinates) { coord, instr -> coord.foldPaper(instr) }

fun List<String>.coordinates() = map { it.split(',')[0].toInt() to it.split(',')[1].toInt() }.toSet()

fun List<String>.instructions() =
    map { Instruction(it.split('=')[0].last(), it.split('=')[1].toInt()) }

fun Set<Pair<Int, Int>>.foldPaper(instruction: Instruction) =
    if (instruction.direction == 'x') verticalFold(instruction) else horizontalFold(instruction)

fun Set<Pair<Int, Int>>.horizontalFold(instruction: Instruction) =
    filterNot { it.second == instruction.line }
        .map { if (it.second > instruction.line) it.first to instruction.line - (it.second - instruction.line) else it }
        .toSet()

fun Set<Pair<Int, Int>>.verticalFold(instruction: Instruction) =
    filterNot { it.first == instruction.line }
        .map { if (it.first > instruction.line) instruction.line - (it.first - instruction.line) to it.second else it }
        .toSet()

fun Set<Pair<Int, Int>>.print() =
    (0..this.maxOf { it.second }).map { y ->
        (0..this.maxOf { it.first }).joinToString("") { x -> if (x to y in this) "███" else "   " }
    }