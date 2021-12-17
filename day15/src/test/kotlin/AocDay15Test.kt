import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay15Test {

    private val exampleInput = listOf(
        "1163751742",
        "1381373672",
        "2136511328",
        "3694931569",
        "7463417111",
        "1319128137",
        "1359912421",
        "3125421639",
        "1293138521",
        "2311944581"
    )

    @Test
    internal fun `solutionPart1 example input`() {
        assertEquals(40, solutionPart1(exampleInput))
    }

    @Test
    internal fun `solutionPart2 example input`() {
        assertEquals(315, solutionPart2(exampleInput))
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(553, solutionPart1(File("input.txt").readLines()))
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(2858, solutionPart2(File("input.txt").readLines()))
    }
}
