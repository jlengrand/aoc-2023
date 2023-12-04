fun main() {

    data class Part(val number: Int, val startIndex : Int, val endIndex : Int, val line: Int? = null)
    data class Symbol(val symbol: Char, val x: Int, val y: Int)

    fun getPartsFromLines(input: List<String>): List<Part> {

        val regexp = "\\d+".toRegex()
        return input.mapIndexed { index, s ->
            regexp.findAll(s)
                .toList()
                .map { Part(it.value.toInt(), it.range.first, it.range.last, index) }
        }.flatten()
    }

    fun part1(input: List<String>) : Int {

        val symbols = input.map {
                line -> line.indices.filter { line[it] != '.' && !line[it].isDigit() }
        }

        val symbols2 = symbols.mapIndexed { index, ints ->
            ints.map { i -> Symbol(input[index][i], index, i) }
        }.flatten()

        val values = getPartsFromLines(input)
        val finalParts = mutableListOf<Part>()

        symbols2.forEach { symbol ->

                val symbolXRange = symbol.x-1..symbol.x+1
                val symbolYRange = symbol.y-1..symbol.y+1

                for (part in values) {

                    if (part.line in symbolXRange){
                        if (part.startIndex in symbolYRange || part.endIndex in symbolYRange){
                            finalParts.add(part)
                        }
                    }
                }
        }

        return finalParts.toSet().sumBy { it.number }
    }

    fun part2(input: List<String>) : Int {

        val symbols = input.map {
                line -> line.indices.filter { line[it] != '.' && !line[it].isDigit() }
        }

        val symbols2 = symbols.mapIndexed { index, ints ->
            ints.map { i -> Symbol(input[index][i], index, i) }
        }.flatten()
            .filter { it.symbol == '*' }

        val values = getPartsFromLines(input)

        var gearRatio = 0

        symbols2.forEach { symbol ->

            val symbolXRange = symbol.x-1..symbol.x+1
            val symbolYRange = symbol.y-1..symbol.y+1

            val gearparts = mutableListOf<Part>()
            for (part in values) {

                if (part.line in symbolXRange){
                    if (part.startIndex in symbolYRange || part.endIndex in symbolYRange){
                        gearparts.add(part)
                    }
                }
            }

            if (gearparts.size == 2){
                gearRatio += gearparts[0].number * gearparts[1].number
            }
            gearparts.clear()
        }
        return gearRatio
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part1(input).println()

    check(part2(testInput) == 467835)
    part2(input).println()

}
