fun main() {
    fun read(input: List<String>): List<Int> {
        var input1 = input
        val list: MutableList<Int> = arrayListOf()
        var i = 0
        repeat(input1.size) {
            i += 1
            list.add(input1.takeWhile { elem -> elem != "" }.sumOf { it.toInt() })
            input1 = input1.dropWhile { elem -> elem != "" }.drop(1)
        }
        return list
    }

    fun part1(input: List<String>): Int {
        return read(input).max()
    }

    fun part2(input: List<String>): Int {
        return read(input).sorted().takeLast(3).sum()
    }

//    test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01.txt")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
