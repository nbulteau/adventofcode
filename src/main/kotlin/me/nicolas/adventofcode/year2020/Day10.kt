package me.nicolas.adventofcode.year2020

import me.nicolas.adventofcode.readFileDirectlyAsText

// --- Day 10: Adapter Array ---
// https://adventofcode.com/2020/day/10
fun main() {

    println("--- Day 10: Adapter Array ---")
    println()

    val training = readFileDirectlyAsText("/year2020/day10/training.txt")
    val data = readFileDirectlyAsText("/year2020/day10/data.txt")

    val adapters = data.split("\n").map { str -> str.toInt() }

    // Part One
    partOne(adapters)

    // Part Two
    partTwo(adapters)
}

private fun partOne(numbers: List<Int>) {
    val dif1 = mutableListOf<Int>()
    val dif3 = mutableListOf<Int>()

    var current = 0
    do {
        if ((current + 1) in numbers) {
            current += 1
            dif1.add(current)
        } else {
            current += 3
            dif3.add(current)
        }
    } while (current <= numbers.maxOf { it })

    println("Part one (${dif1.size} * ${dif3.size}) = ${dif1.size * dif3.size}")
}

private fun partTwo(list: List<Int>) {

    // init all adapters
    val adapters = list.toMutableList().apply {
        add(0) // 0
        add(list.maxOf { it } + 3)
    }

    // init all possible paths map
    val allPossiblePaths: MutableMap<Int, Long> = adapters.map { adapter -> adapter to 0L }.toMap().toMutableMap()
    allPossiblePaths[adapters.maxOf { it }] = 1

    // number of possible paths is the sum of child possible paths
    for (adapter in adapters.sortedDescending()) {
        for (index in 1..3) {
            if ((adapter + index) in adapters) {
                allPossiblePaths[adapter] = allPossiblePaths[adapter]!! + allPossiblePaths[adapter + index]!!
            }
        }
    }

    println("Part two = ${allPossiblePaths[0]}")
}
