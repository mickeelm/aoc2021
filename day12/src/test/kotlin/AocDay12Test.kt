import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay12Test {

    private val smallExampleInput = listOf(
        "start-A",
        "start-b",
        "A-c",
        "A-b",
        "b-d",
        "A-end",
        "b-end"
    )
    private val mediumExampleInput = listOf(
        "dc-end",
        "HN-start",
        "start-kj",
        "dc-start",
        "dc-HN",
        "LN-dc",
        "HN-end",
        "kj-sa",
        "kj-HN",
        "kj-dc"
    )
    private val largeExampleInput = listOf(
        "fs-end",
        "he-DX",
        "fs-he",
        "start-DX",
        "pj-DX",
        "end-zg",
        "zg-sl",
        "zg-pj",
        "pj-he",
        "RW-he",
        "fs-DX",
        "pj-RW",
        "zg-RW",
        "start-pj",
        "he-WI",
        "zg-he",
        "pj-fs",
        "start-RW"
    )

    @Test
    internal fun `solutionPart1 small example input`() {
        assertEquals(10, solutionPart1(smallExampleInput))
    }

    @Test
    internal fun `solutionPart1 medium example input`() {
        assertEquals(19, solutionPart1(mediumExampleInput))
    }

    @Test
    internal fun `solutionPart1 large example input`() {
        assertEquals(226, solutionPart1(largeExampleInput))
    }

    @Test
    internal fun `solutionPart2 small example input`() {
        assertEquals(36, solutionPart2(smallExampleInput))
    }

    @Test
    internal fun `solutionPart2 medium example input`() {
        assertEquals(103, solutionPart2(mediumExampleInput))
    }

    @Test
    internal fun `solutionPart2 large example input`() {
        assertEquals(3509, solutionPart2(largeExampleInput))
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(5252, solutionPart1(File("input.txt").readLines()))
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(147784, solutionPart2(File("input.txt").readLines()))
    }
}
