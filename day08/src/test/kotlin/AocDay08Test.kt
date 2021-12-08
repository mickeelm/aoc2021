import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay06Test {

    private val smallExampleInput =
        listOf("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")

    private val largeExampleInput = listOf(
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
        "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
        "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
        "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
        "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
        "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
        "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
        "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
        "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
    )

    @Test
    internal fun `solutionPart1 example input`() {
        assertEquals(26, solutionPart1(largeExampleInput))
    }

    @Test
    internal fun `solutionPart2 small example input`() {
        assertEquals(5353, solutionPart2(smallExampleInput))
    }

    @Test
    internal fun `solutionPart2 large example input`() {
        assertEquals(61229, solutionPart2(largeExampleInput))
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(452, solutionPart1(File("input.txt").readLines()))
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(1096964, solutionPart2(File("input.txt").readLines()))
    }
}
