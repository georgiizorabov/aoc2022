fun main() {
    fun read(input: String, size: Int) =
        input.windowed(size, 1).indexOfFirst { elem -> elem.toList().distinct().size == size } + size

    fun part1(input: List<String>) = read(input[0], 4)

    fun part2(input: List<String>) = read(input[0], 14)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}