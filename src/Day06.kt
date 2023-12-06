fun main() {

    data class Race(val time: Long, val distance: Long)

    fun part1(input: List<String>) : Int {
        val times = input[0].split(":")[1].split(" ").filter{ it.isNotEmpty() }.map { it.toLong() }
        val distances = input[1].split(":")[1].split(" ").filter{ it.isNotEmpty() }.map { it.toLong() }

        val races = times.zip(distances).map { Race(it.first, it.second) }

        val results = mutableListOf<Int>()
        for (race in races){
            val possibleTimes = 0..race.time
            val possibleDistances = possibleTimes.map {
                it * (possibleTimes.last - it)
            }.toList()

            val possibleRaces = possibleTimes.zip(possibleDistances).map { Race(it.first, it.second) }
            val winningRaces = possibleRaces.filter{ zeRace -> zeRace.distance > race.distance }

            results += winningRaces.size
        }

        return results.reduce { acc, i ->  acc * i }

    }

    fun part2(input: List<String>) : Int {

        val times = input[0].split(":")[1].filter { !it.isWhitespace() }.toLong()
        val distances = input[1].split(":")[1].filter { !it.isWhitespace() }.toLong()

        val race = Race(times, distances)

        val possibleTimes = 0..race.time
        val possibleDistances = possibleTimes.map {
            it * (possibleTimes.last - it)
        }.toList()

        val possibleRaces = possibleTimes.zip(possibleDistances).map { Race(it.first, it.second) }
        val winningRaces = possibleRaces.filter{ zeRace -> zeRace.distance > race.distance }

        return winningRaces.size
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 288)

    val input = readInput("Day06")
    part1(input).println()

    check(part2(testInput) == 71503)

    part2(input).println()
}
