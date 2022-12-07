import java.lang.Double.min
import kotlin.math.min

fun main() {
    class Node(
        val parent: Node?,
        val files: MutableList<Pair<String, Int>> = ArrayList(),
        val dirs: MutableMap<String, Node> = mutableMapOf(),
        var size: Int = 0
    )

    var root: Node? = null
    root = Node(root)
    var curNode = root

    fun processCommand(splittedCommand: List<String>) {
        when (splittedCommand[1]) {
            "cd" -> {
                if (splittedCommand[2] == "/") {
                    curNode = root
                } else if (splittedCommand[2] == "..") {
                    curNode = curNode!!.parent
                } else {
                    curNode = curNode!!.dirs[splittedCommand[2]]
                }
            }

            "ls" -> {
                // skip
            }

        }
    }

    fun processCreateDir(splittedCommand: List<String>) {
        curNode!!.dirs[splittedCommand[1]] = Node(curNode)
    }

    var sum = 0

    fun processCreateFile(splittedCommand: List<String>) {
        sum += splittedCommand[0].toInt()
        curNode!!.files.add(Pair(splittedCommand[1], splittedCommand[0].toInt()))
    }

    fun processInput(command: String) {
        val splittedCommand = command.split(" ")
        when (splittedCommand[0]) {
            "$" -> processCommand(splittedCommand)
            "dir" -> processCreateDir(splittedCommand)
            else -> processCreateFile(splittedCommand)
        }
    }

    var cnt = 0
    var min = 1_000_000_000
    var diff = 0

    fun dfs(curNode: Node) {
        if (curNode.dirs.isNotEmpty()) {
            for (dir in curNode.dirs.values) {
                dfs(dir)
            }
        }

        curNode.size = curNode.files.sumOf { it.second } + curNode.dirs.values.sumOf { it.size }

        if (curNode.size <= 100000) {
            cnt += curNode.size
        }
    }

    fun dfs1(curNode: Node) {
        if (curNode.dirs.isNotEmpty()) {
            val m = curNode.dirs.values.filter { it.size >= diff }.minOfOrNull { it.size }
            if (m != null) {
                min = min(min, m)
            }
            for (dir in curNode.dirs.values) {
                dfs1(dir)
            }
        }
    }

    fun part1(input: List<String>): Int {
        input.forEach { processInput(it) }
        dfs(root)
        return cnt
    }

    fun part2(input: List<String>): Int {
        input.forEach { processInput(it) }
        dfs(root)
        diff = -40000000 + sum / 2
        dfs1(root)
        return min
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
