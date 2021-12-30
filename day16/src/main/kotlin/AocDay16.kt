import java.io.File

fun main() {
    val input = File("input.txt").readLines().first()
    val answer = when (System.getenv("part")) {
        "part2" -> solutionPart2(input)
        else -> solutionPart1(input)
    }
    println(answer)
}

class PacketStream(private val stream: ArrayDeque<UInt>) {
    private var bufferSize = 0
    private var buffer = ULong.MIN_VALUE
    private var position = 0

    fun next(numberOfBits: Int = 1): Int {
        if (bufferSize - numberOfBits < 0) {
            buffer = buffer shl 32 or stream.removeFirst().toULong()
            bufferSize += 32
        }
        bufferSize -= numberOfBits
        val requestedBits = buffer shr bufferSize
        buffer = if (bufferSize == 0) ULong.MIN_VALUE else buffer and (ULong.MAX_VALUE shr ULong.SIZE_BITS - bufferSize)
        position += numberOfBits

        return requestedBits.toInt()
    }

    fun position() = position
}

data class Packet(val versionSum: Int = 0, val value: Long = 0) {
    operator fun plus(other: Packet) = Packet(versionSum + other.versionSum, value + other.value)
    operator fun times(other: Packet) = Packet(versionSum + other.versionSum, value * other.value)
    fun min(other: Packet) = Packet(versionSum + other.versionSum, minOf(value, other.value))
    fun max(other: Packet) = Packet(versionSum + other.versionSum, maxOf(value, other.value))
    fun greaterThan(other: Packet) = Packet(versionSum + other.versionSum, if (value > other.value) 1 else 0)
    fun lessThan(other: Packet) = Packet(versionSum + other.versionSum, if (value < other.value) 1 else 0)
    fun equalTo(other: Packet) = Packet(versionSum + other.versionSum, if (value == other.value) 1 else 0)
}

fun solutionPart1(input: String) = input.asPacketStream().parse().versionSum
fun solutionPart2(input: String) = input.asPacketStream().parse().value

fun String.asPacketStream() =
    PacketStream(chunked(8) { it.toString().padEnd(8, '0').toUInt(16) }.toCollection(ArrayDeque()))

fun PacketStream.parse(): Packet {
    val version = next(3)
    return when (val typeId = next(3)) {
        0 -> Packet(version) + operator().reduce { acc, packet -> acc + packet }
        1 -> Packet(version) + operator().reduce { acc, packet -> acc * packet }
        2 -> Packet(version) + operator().reduce { acc, packet -> acc.min(packet) }
        3 -> Packet(version) + operator().reduce { acc, packet -> acc.max(packet) }
        4 -> Packet(version, literal())
        5 -> Packet(version) + operator().let { it.first().greaterThan(it.last()) }
        6 -> Packet(version) + operator().let { it.first().lessThan(it.last()) }
        7 -> Packet(version) + operator().let { it.first().equalTo(it.last()) }
        else -> throw RuntimeException("Invalid type id $typeId")
    }
}

fun PacketStream.literal(): Long {
    var value = 0L
    do {
        val group = next(5).toLong()
        value = value shl 4 or (group and 15)
    } while (group shr 4 == 1L)

    return value
}

fun PacketStream.operator(): ArrayDeque<Packet> {
    val packetProperties = ArrayDeque<Packet>()
    if (next() == 0) {
        val length = next(15)
        val currentPosition = position()
        while (position() < currentPosition + length) {
            packetProperties += parse()
        }
    } else {
        repeat(next(11)) { packetProperties += parse() }
    }

    return packetProperties
}
