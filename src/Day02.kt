import java.util.concurrent.ConcurrentHashMap

val RPCUnitsToInt = hashMapOf(
    'A' to 1,
    'B' to 2,
    'C' to 3,
    'X' to 1,
    'Y' to 2,
    'Z' to 3
)
val IntToRPCUnits = hashMapOf(
    0 to 'Z',
    1 to 'X',
    2 to 'Y',
    3 to 'Z'
)

fun compare1(unit1: Char, unit2: Char): Int {
    val realUnit2: Char = if (unit2 == 'Z') {
        IntToRPCUnits[RPCUnitsToInt[unit1]?.plus(1)?.rem(3)]!!
    } else if (unit2 == 'Y') {
        IntToRPCUnits[RPCUnitsToInt[unit1]]!!
    } else {
        IntToRPCUnits[RPCUnitsToInt[unit1]?.minus(1)?.rem(3)]!!
    }
    return compare(unit1, realUnit2)
}
fun compare(unit1: Char, unit2: Char): Int {
    val res = RPCUnitsToInt[unit2]
    return if (RPCUnitsToInt[unit1] == RPCUnitsToInt[unit2]) {
        res!! + 3
    } else if (RPCUnitsToInt[unit1]?.plus(1)?.rem(3) == RPCUnitsToInt[unit2]?.rem(3)) {
        res!! + 6
    } else {
        res!!
    }
}



fun main() {

    fun read(input: List<String>): List<Pair<Char, Char>> {
        return input.map { str -> Pair(str[0], str[2]) }
    }

    fun part1(input: List<String>): Int {
        return read(input).sumOf { pair -> compare(pair.first, pair.second) }
    }

    fun part2(input: List<String>): Int {
        return read(input).sumOf { pair -> compare1(pair.first, pair.second) }
    }


    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}