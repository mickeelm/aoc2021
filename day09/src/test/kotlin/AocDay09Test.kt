import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay09Test {

    private val exampleInput = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678"
    )

    @Test
    internal fun `solutionPart1 example input`() {
        assertEquals(15, solutionPart1(exampleInput))
    }

    @Test
    internal fun `solutionPart2 example input`() {
        assertEquals(1134, solutionPart2(exampleInput))
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(564, solutionPart1(File("input.txt").readLines()))
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(1038240, solutionPart2(File("input.txt").readLines()))
    }
}
