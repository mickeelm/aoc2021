import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay06Test {

    private val exampleInput = "3,4,3,1,2"

    @Test
    internal fun `solutionPart1 example input`() {
        assertEquals(5934, solutionPart1(exampleInput))
    }

    @Test
    internal fun `solutionPart2 example input`() {
        assertEquals(26984457539L, solutionPart2(exampleInput))
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(362740, solutionPart1(File("input.txt").readLines()[0]))
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(1644874076764, solutionPart2(File("input.txt").readLines()[0]))
    }
}
