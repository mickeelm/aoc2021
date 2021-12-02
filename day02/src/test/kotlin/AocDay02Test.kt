import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay02Test {

    private val exampleInput = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )

    @Test
    internal fun `solutionPart1 example input`() {
        assertEquals(solutionPart1(exampleInput), 150)
    }

    @Test
    internal fun `solutionPart2 example input`() {
        assertEquals(solutionPart2(exampleInput), 900)
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(solutionPart1(File("input.txt").readLines()), 1604850)
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(solutionPart2(File("input.txt").readLines()), 1685186100)
    }
}
