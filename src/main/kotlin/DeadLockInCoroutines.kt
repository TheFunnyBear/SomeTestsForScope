package org.example

import kotlinx.coroutines.*

class DeadLockInCoroutines() {

    private val scope = CoroutineScope(Dispatchers.Default)

    private fun computeString(index: Int): String {
        val completableDeferred = CompletableDeferred<Int>()
        scope.launch {
            completableDeferred.complete(index)
        }
        val number = runBlocking {
            completableDeferred.await()
        }
        return "String from: $index. Number: $number"
    }

    fun start() {
        println("My test coroutine runs...:  ${Thread.currentThread().name}")

        val jobs = ArrayList<Deferred<String>>()
        // Repeat count should be higher than the number of cores
        repeat(50) { i ->
            val completableDeferred = CompletableDeferred<String>()
            scope.launch {
                completableDeferred.complete(computeString(i))
            }
            jobs.add(completableDeferred)
        }

        runBlocking {
            jobs.forEach { d ->
                val text = d.await()
                println("Result: $text")
            }
        }

        println("My test run ends...: ${Thread.currentThread().name}")
    }

}