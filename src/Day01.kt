fun main() {

    fun replaceNumbers(string : String): String {
        return string
            .replace("one", "one1one")
            .replace("two", "two2two")
            .replace("three", "three3three")
            .replace("four", "four4four")
            .replace("five", "five5five")
            .replace("six", "six6six")
            .replace("seven", "seven7seven")
            .replace("eight", "eight8eight")
            .replace("nine", "nine9nine")
    }

    fun part1(input: List<String>) = input
            .map { line -> line.filter { it.isDigit() } }
            .map { "${it.first()}${it.last()}" }.sumOf { it.toInt() }

    fun part2(input: List<String>) = input
            .map { replaceNumbers(it) }
            .map { line -> line.filter { it.isDigit() } }
            .map { "${it.first()}${it.last()}" }.sumOf { it.toInt() }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    part2(input).println()
}