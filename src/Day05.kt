
fun main() {
    fun readStacks(input: List<String>): List<List<Char>> {
        val stacks =
            input.takeWhile { it != "" }.map { it.toList().filterIndexed { index, _ -> index % 4 == 1 } }.dropLast(1)
        val stacksConv: MutableList<MutableList<Char>> =
            Array(stacks.maxBy { it.size }.size) { ArrayList<Char>() }.toMutableList()
        stacks.forEach { it.withIndex().forEach { elem -> stacksConv[elem.index].add(elem.value) } }
        return stacksConv.map { it.filter { elem -> elem != ' ' } }
    }

    fun makeCommand(stacks: List<List<Char>>, command: List<String>, crateMoverVersion: Int = 9000): List<List<Char>> {
        val newStacks = stacks.toMutableList()
        var stacksToMove =
            newStacks[command[3].toInt() - 1].withIndex().takeWhile { it.index <= command[1].toInt() - 1 }
                .map { it.value }
        newStacks[command[3].toInt() - 1] =
            newStacks[command[3].toInt() - 1].withIndex()
                .map { if (it.index <= command[1].toInt() - 1) ' ' else it.value }

        if (crateMoverVersion == 9000) {
            stacksToMove = stacksToMove.reversed()
        }
        newStacks[command[5].toInt() - 1] =
            stacksToMove + newStacks[command[5].toInt() - 1]
        return newStacks.map { it.filter { elem -> elem != ' ' } }

    }

    fun readCommands(input: List<String>): List<List<String>> {
        return input.dropWhile { it != "" }
            .map { it.filter { elem -> elem.isDigit() || elem == ' ' }.split(" ") }.drop(1)
    }

    fun solve(input: List<String>, crateMoverVersion: Int = 9000): String {
        val stacks = (readStacks(input))
        val commands = readCommands(input)

        var mutableStacks = stacks.toMutableList()
        for (command in commands) {
            mutableStacks = makeCommand(mutableStacks, command, crateMoverVersion).toMutableList()
        }

        return mutableStacks.map { it.first() }.joinToString(separator = "")
    }

    fun part1(input: List<String>): String {
        return solve(input)
    }

    fun part2(input: List<String>): String {
        return solve(input, 9001)
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}