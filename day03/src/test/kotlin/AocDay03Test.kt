import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay03Test {

    private val exampleInput = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @Test
    internal fun `solutionPart1 example input`() {
        assertEquals( 198, solutionPart1(exampleInput))
    }

    @Test
    internal fun `solutionPart2 example input`() {
        assertEquals(solutionPart2(exampleInput), 230)
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(solutionPart1(File("input.txt").readLines()), 1071734)
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(solutionPart2(File("input.txt").readLines()), 6124992)
    }
}
