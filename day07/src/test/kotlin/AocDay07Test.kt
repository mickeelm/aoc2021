import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay07Test {

    private val exampleInput = "16,1,2,0,4,2,7,1,2,14"

    @Test
    internal fun `solutionPart1 example input`() {
        assertEquals(37, solutionPart1(exampleInput))
    }

    @Test
    internal fun `solutionPart2 example input`() {
        assertEquals(168, solutionPart2(exampleInput))
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(347011, solutionPart1(File("input.txt").readLines()[0]))
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(98363777, solutionPart2(File("input.txt").readLines()[0]))
    }
}
