package org.example

import kotlin.random.Random

class Payload () {
    companion object {
        fun doSomeCalc(repeat: Int = 100_000, eatMemory: Boolean = true): Long {
            var summary = 0L
            repeat(repeat) {
                val toRange = if (eatMemory) 100_000_000 else 100
                val randomValues = (1..toRange).map { Random.nextInt() }
                randomValues.forEach { value -> summary += value }
                Thread.sleep(100)
            }
            return summary
        }
    }
}