import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class AocDay16Test {

    @Test
    internal fun `solutionPart1 example input 1`() {
        assertEquals(6, solutionPart1("D2FE28"))
    }

    @Test
    internal fun `solutionPart1 example input 2`() {
        assertEquals(9, solutionPart1("38006F45291200"))
    }

    @Test
    internal fun `solutionPart1 example input 3`() {
        assertEquals(14, solutionPart1("EE00D40C823060"))
    }

    @Test
    internal fun `solutionPart1 example input 4`() {
        assertEquals(16, solutionPart1("8A004A801A8002F478"))
    }

    @Test
    internal fun `solutionPart1 example input 5`() {
        assertEquals(12, solutionPart1("620080001611562C8802118E34"))
    }

    @Test
    internal fun `solutionPart1 example input 6`() {
        assertEquals(23, solutionPart1("C0015000016115A2E0802F182340"))
    }

    @Test
    internal fun `solutionPart1 example input 7`() {
        assertEquals(31, solutionPart1("A0016C880162017C3686B18A3D4780"))
    }

    @Test
    internal fun `solutionPart1 real input`() {
        assertEquals(897, solutionPart1(File("input.txt").readLines().first()))
    }

    @Test
    internal fun `solutionPart2 example input 1`() {
        assertEquals(3, solutionPart2("C200B40A82"))
    }

    @Test
    internal fun `solutionPart2 example input 2`() {
        assertEquals(54, solutionPart2("04005AC33890"))
    }

    @Test
    internal fun `solutionPart2 example input 3`() {
        assertEquals(7, solutionPart2("880086C3E88112"))
    }

    @Test
    internal fun `solutionPart2 example input 4`() {
        assertEquals(9, solutionPart2("CE00C43D881120"))
    }

    @Test
    internal fun `solutionPart2 example input 5`() {
        assertEquals(1, solutionPart2("D8005AC2A8F0"))
    }

    @Test
    internal fun `solutionPart2 example input 6`() {
        assertEquals(0, solutionPart2("F600BC2D8F"))
    }

    @Test
    internal fun `solutionPart2 example input 7`() {
        assertEquals(0, solutionPart2("9C005AC2F8F0"))
    }

    @Test
    internal fun `solutionPart2 example input 8`() {
        assertEquals(1, solutionPart2("9C0141080250320F1802104A08"))
    }

    @Test
    internal fun `solutionPart2 example input appendix A`() {
        assertEquals(1, solutionPart2("38006F45291200"))
    }

    @Test
    internal fun `solutionPart2 example input appendix B`() {
        assertEquals(2021, solutionPart2("D2FE28"))
    }

    @Test
    internal fun `solutionPart2 real input`() {
        assertEquals(9485076995911, solutionPart2(File("input.txt").readLines().first()))
    }
}
