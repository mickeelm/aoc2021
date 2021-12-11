import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay11Test {

    private val exampleInput = listOf(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526"
    )

    @Test
    internal fun `solutionPart1 example input`() {
        assertEquals(1656, solutionPart1(exampleInput))
    }

    @Test
    internal fun `solutionPart2 example input`() {
        assertEquals(195, solutionPart2(exampleInput))
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(1741, solutionPart1(File("input.txt").readLines()))
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(440, solutionPart2(File("input.txt").readLines()))
    }
}
