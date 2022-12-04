fun main() {

    fun read(input: List<String>): List<Pair<String, String>> {
        println(input)
        return input.map { str ->
            Pair(
                str.split(",")[0],
                str.split(",")[1]
            )
        }
    }
    fun pred(it: Pair<String, String>): Boolean {
        val it1 = Pair(it.first.split('-'), it.second.split('-'))
        return (it1.first[0].toInt() <= it1.second[0].toInt() && it1.first[1].toInt() >= it1.second[1].toInt()) || (it1.first[0].toInt() >= it1.second[0].toInt() && it1.first[1].toInt() <= it1.second[1].toInt())
    }
    fun pred1(it: Pair<String, String>): Boolean {
        val it1 = Pair(it.first.split('-'), it.second.split('-'))
        return (it1.first[0].toInt() >= it1.second[0].toInt() && it1.first[0].toInt() <= it1.second[1].toInt()) || (it1.second[0].toInt() >= it1.first[0].toInt() && it1.second[0].toInt() <= it1.first[1].toInt())
    }

    fun part1(input: List<String>): Int {
        println(read(input).filter { pred(it) })
        return read(input).filter { pred(it) }.size
    }

    fun part2(input: List<String>): Int {
        return read(input).filter { pred1(it) }.size
    }


    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}