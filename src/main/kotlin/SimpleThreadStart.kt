package org.example

import kotlin.concurrent.thread
import kotlin.random.Random

class SimpleThreadStart() {

    fun start() {
        println("My program runs...:  ${Thread.currentThread().name}")

        thread {
            longRunningTask()
        }

        println("My program run ends...: ${Thread.currentThread().name}")
    }

    private fun longRunningTask(){
        println("executing longRunningTask on...: ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("longRunningTask ends on thread ...: ${Thread.currentThread().name}")
    }


}