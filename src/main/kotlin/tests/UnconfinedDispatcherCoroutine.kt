package org.example.tests

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

class UnconfinedDispatcherCoroutine() {
    fun start() {
        println("My test runs...:  ${Thread.currentThread().name}")

        /**
         * CoroutineScope (Dispatchers.Unconfined)
         * Особенность Unconfined диспетчера заключается в том, что корутина
         * не закрепляется чётко за определённым потоком или пулом потоков.
         * Она запускается в текущем потоке до первой приостановки.
         * После возобновления работы корутина продолжает работу в одном из потоков,
         * который строго не фиксирован.
         */
        val unconfinedScope = CoroutineScope(Dispatchers.Unconfined)
        unconfinedScope.launch {
            repeat(10) {
                println("I'm working in thread ${Thread.currentThread().name} and id:${Thread.currentThread().id}")
                delay(500)
            }
        }

        /**
         * Ждём, чтобы убедиться что всё завершилось
         */
        Thread.sleep(15.seconds.inWholeMilliseconds)
        unconfinedScope.cancel()

        val defaultScope = CoroutineScope(Dispatchers.Default)
        val ioScope = CoroutineScope(Dispatchers.IO)


        /**
         * Если scope сделан cancel - то в нём больше нельзя запустить корутины
         */
        val scopes = listOf(defaultScope, ioScope, unconfinedScope)
        scopes.forEach { scope ->
            scope.launch {
                repeat(10) {
                    println("In scope: ${scope.coroutineContext} -> I'm working in thread ${Thread.currentThread().name} and id:${Thread.currentThread().id}")
                    delay(500)
                }
            }
        }

        Thread.sleep(15.seconds.inWholeMilliseconds)

        println("My test ends...: ${Thread.currentThread().name}")
    }


}