import kotlin.math.pow

fun main() {

    data class Card(val winning: List<Int>, val numbers : List<Int>)

    fun getNumbers(input: String) = input.split(" ").filter{ it.isNotEmpty() }.map { it.toInt() }

    fun cardFromLine(input: String): Card {

        val winningAndNumbers = input
            .split(":")[1]
            .split("|")

        return Card(getNumbers(winningAndNumbers[0]), getNumbers(winningAndNumbers[1]))
    }

    fun calculatePointsFromMatches(matches : Int): Int =
        if(matches == 0) 0
        else 2.0.pow(matches.toDouble() - 1).toInt()


    fun part1(input: List<String>) =
        input
            .map { cardFromLine(it) }
            .map { it.winning.intersect(it.numbers.toSet()).size }
            .sumOf { calculatePointsFromMatches(it) }


    fun part2(input: List<String>) : Int {

        val cards = input.map { it -> Pair(1, cardFromLine(it)) }

        val cardsToTry = cards.toMutableList()
        var totalCards = 0

        while (cardsToTry.isNotEmpty()){
            val card = cardsToTry.first()
            cardsToTry.removeFirst()

            totalCards += card.first

            val matches = card.second.winning.intersect(card.second.numbers).size

            for (i in 0 until matches){
                val nextCard = cardsToTry[i]
                cardsToTry[i] = Pair(nextCard.first + card.first, nextCard.second)
            }
        }
        return totalCards
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    check(part1(testInput) == 13)
    part1(input).println()

    check(part2(testInput) == 30)
    part2(input).println()
}
