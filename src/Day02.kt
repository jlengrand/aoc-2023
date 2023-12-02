fun main() {

    data class Pick(val blue: Int, val green: Int, val red: Int)
    data class Game(val gameNumber: Int, val picks: List<Pick>)

    fun pickFromInput(input: String): Pick {
        val colors = input.split(",")

        var blue = 0
        var green = 0
        var red = 0

        colors.forEach {
            when {
                it.contains("green") -> green =  it.filter { c -> c.isDigit() }.toInt()
                it.contains("blue") -> blue = it.filter { c -> c.isDigit() }.toInt()
                it.contains("red") -> red = it.filter { c -> c.isDigit() }.toInt()
            }
        }

        return Pick(blue, green, red)
    }

    fun structureGame(input: String): Game {
        val gameAndPicks = input.split(":")
        val gameNumber = gameAndPicks[0].removePrefix("Game ").toInt()
        val allPicks = gameAndPicks[1].split(";")

        return Game(gameNumber, allPicks.map { pickFromInput(it) })
    }

    fun gameIsPossible(game: Game, maxPick: Pick): Boolean {
        return game.picks.all { pick ->
            pick.blue <= maxPick.blue &&
            pick.green <= maxPick.green &&
            pick.red <= maxPick.red
        }
    }

    fun checkMinPick(game: Game) : Pick{
        var minBlue = 0
        var minGreen = 0
        var minRed = 0

        game.picks.forEach { pick ->
            if (pick.blue > minBlue) minBlue = pick.blue
            if (pick.green > minGreen) minGreen = pick.green
            if (pick.red > minRed) minRed = pick.red
        }

        return Pick(minBlue, minGreen, minRed)
    }

    fun part1(input: List<String>) : Int {

        val games = input.map { structureGame(it) }

        val possibleGames = games.filter { gameIsPossible(it, Pick(14, 13, 12)) }

        return possibleGames.sumOf { it.gameNumber }
    }

    fun part2(input: List<String>) : Int {
        val games = input.map { structureGame(it) }

        val minPicks  = games.map { checkMinPick(it) }

        return minPicks.sumOf { it.blue * it.green * it.red }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()

    val testInput2 = readInput("Day02_test")
    check(part2(testInput2) == 2286)

    part2(input).println()
}
