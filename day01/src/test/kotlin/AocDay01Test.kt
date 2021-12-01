import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay01Test {

    @Test
    internal fun `solutionPart1 example input`() {
        assertEquals(solutionPart1(listOf(199,200,208,210,200,207,240,269,260,263)), 7)
    }

    @Test
    internal fun `solutionPart2 example input`() {
        assertEquals(solutionPart2(listOf(199,200,208,210,200,207,240,269,260,263)), 5)
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(solutionPart1(File("input.txt").readLines().map { it.toInt() }), 1228)
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(solutionPart2(File("input.txt").readLines().map { it.toInt() }), 1257)
    }
}