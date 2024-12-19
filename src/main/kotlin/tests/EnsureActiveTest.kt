package org.example.tests

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class EnsureActiveTest() {

    fun start() {
        println("My test coroutine runs...:  ${Thread.currentThread().name}")

        var pingInterval = System.currentTimeMillis()
        val scope = CoroutineScope(Dispatchers.IO)
        val job = scope.launch {
            var index = 0
            while (true) {
                if (System.currentTimeMillis() > pingInterval) {
                    println("I am alive... ${index++}")
                    pingInterval += 1000L
                }
            }
        }

        Thread.sleep(10.seconds.inWholeMilliseconds)

        job.cancel()
        println("Cancel job")

        Thread.sleep(10.seconds.inWholeMilliseconds)

        val jobWithEnsureActive = scope.launch {
            var index = 0
            while (true) {
                ensureActive()
                if (System.currentTimeMillis() > pingInterval) {
                    println("I am alive with ensureActive... ${index++}")
                    pingInterval += 1000L
                }
            }
        }

        Thread.sleep(10.seconds.inWholeMilliseconds)

        jobWithEnsureActive.cancel()
        println("Cancel jobWithEnsureActive")

        Thread.sleep(10.seconds.inWholeMilliseconds)

        println("My test run ends...: ${Thread.currentThread().name}")
    }
}