import Direction.*
import Position.Companion.START
import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

fun solutionPart1(input: List<String>) = input.map { it.command() }
    .fold(START) { position, command -> position.move(command) }.product

fun solutionPart2(input: List<String>) = input.map { it.command() }
    .fold(START) { position, command -> position.moveWithAim(command) }.product

private fun String.command(): Command {
    val split = split(" ")
    return Command(Direction.valueOf(split[0].uppercase()), split[1].toInt())
}

data class Command(val direction: Direction, val units: Int)

enum class Direction {
    FORWARD, DOWN, UP
}

class Position(private val horizontalPosition: Int, private val depth: Int, private val aim: Int) {
    companion object {
        val START = Position(0,0,0)
    }

    val product = horizontalPosition * depth

    fun move(command: Command): Position {
        val units = command.units
        return when (command.direction) {
            FORWARD -> Position(horizontalPosition + units, depth, aim)
            DOWN -> Position(horizontalPosition, depth + units, aim)
            UP -> Position(horizontalPosition, depth - units, aim)
        }
    }

    fun moveWithAim(command: Command): Position {
        val units = command.units
        return when (command.direction) {
            FORWARD -> Position(horizontalPosition + units, depth + units * aim, aim)
            DOWN -> Position(horizontalPosition, depth, aim + units)
            UP -> Position(horizontalPosition, depth, aim - units)
        }
    }
}