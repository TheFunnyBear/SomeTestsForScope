package org.example.tests

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class ExceptionInGlobalScopeCoroutine() {

    @OptIn(DelicateCoroutinesApi::class)
    fun start() {
        println("My GlobalScope coroutine runs...:  ${Thread.currentThread().name}")

        GlobalScope.launch {
            simpleCoroutine()
        }

        /**
         * Исключение в корутине запущенной в GlobalScope не приводит к
         * падению приложения, падает только корутина.
         */

        Thread.sleep(10.seconds.inWholeMilliseconds)
        println("My program run ends...: ${Thread.currentThread().name}")
    }

    private suspend fun simpleCoroutine(){
        delay(500L)

        val valueForGenerateException = 0
        val randomValue = Random.nextInt()
        val result = randomValue / valueForGenerateException
        /**
         * Сюда мы не попадём из-за исключения
         */
        println("Failed with $result")
    }
}