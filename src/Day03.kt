fun main() {

    fun read(input: List<String>): List<Pair<String, String>> {
        return input.map { str ->
            Pair(
                str.substring(0 until str.length / 2),
                str.substring(str.length / 2 until str.length)
            )
        }
    }

    fun read1(input: List<String>): List<List<Char>> {
        return input.map { str -> str.toCharArray().distinct() }.chunked(3)
            .map { elems ->
                elems[0].filter { elem -> elem in elems[1] && elem in elems[2] }
            }
    }

    val charsToAlphabetSum =
        { elems: List<Char> -> elems.sumOf { elem -> Character.getNumericValue(elem) + if (elem.isUpperCase()) 17 else -9 } }

    fun part1(input: List<String>): Int {
        val matches = read(input).map { pair ->
            pair.first.toCharArray().distinct().filter { it in pair.second.toCharArray().distinct() }
        }
        return matches.sumOf { charsToAlphabetSum(it) }
    }

    fun part2(input: List<String>): Int {
        return read1(input).sumOf { charsToAlphabetSum(it) }
    }


    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}