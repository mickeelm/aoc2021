import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PreAocTest {

    @Test
    internal fun `solutionPart1 should work with example input`() {
        assertEquals(2421, solutionPart1(listOf(0, 3, 4, 42, 106, 107, 267, 269)))
    }

    @Test
    internal fun `solutionPart2 should work with example input`() {
        assertEquals(335, solutionPart2(listOf(0, 3, 4, 42, 106, 107, 267, 269)))
    }
}