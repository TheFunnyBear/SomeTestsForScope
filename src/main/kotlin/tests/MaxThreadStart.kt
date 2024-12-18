package org.example.tests

import org.example.Payload
import kotlin.concurrent.thread

/**
 * In Java you will hit a limit creating threads when you run out of memory to process them.
 */
class MaxThreadStart() {

    fun start() {
        println("My max threads program runs...:  ${Thread.currentThread().name}")

        (0..1_000_000).forEach { startNumber ->
            println("start thread: $startNumber")
            thread {
                longRunningTask()
            }
        }

        println("My max program run ends...: ${Thread.currentThread().name}")
    }


    private fun longRunningTask(){
        println("executing longRunningTask on...: ${Thread.currentThread().name}")
        val summary = Payload.doSomeCalc()

        println("longRunningTask ends on thread ...: ${Thread.currentThread().name} summary is: $summary")
    }
}

