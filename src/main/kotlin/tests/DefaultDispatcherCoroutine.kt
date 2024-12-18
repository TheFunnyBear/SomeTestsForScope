package org.example.tests

import kotlinx.coroutines.*
import org.example.Payload
import kotlin.time.Duration.Companion.seconds

class DefaultDispatcherCoroutine() {

    @OptIn(DelicateCoroutinesApi::class)
    fun start() {
        println("My GlobalScope coroutine runs...:  ${Thread.currentThread().name}")

        /**
         * CoroutineScope (Dispatchers.Default)
         * он поддерживается общим пулом потоков в jvm и native по умолчанию
         * максимальное количество потоков, используемых этим диспетчером,
         * равно количеству ядер процессора, но не менее двух.
         */
        val scope = CoroutineScope(Dispatchers.Default)

        var launchedCount = 0
        (0..1000).forEach { index ->
            println("Start index: $index coroutine in default scope ")
            scope.launch {
                println("Launched index: $index coroutine in default scope, launched count ${launchedCount++} ")
                Payload.doSomeCalc(repeat = index, eatMemory = false)
                println("End $index coroutine in default scope ")
                launchedCount--
            }
        }

        /**
         * Видно, что корутины стартуют сразу, но их запуск происходит не сразу.
         * Одновременно живёт максимальное количество корутин, используемых этим диспетчером,
         * равно количеству ядер процессора
         */


        Thread.sleep(40.seconds.inWholeMilliseconds)


        /**
         * Отменяем все корутины, которые работают и стартовали, но не запущенны.
         * Которые работают должны доработать.
         */
        scope.cancel()
        println("Scope canceled!")

        /**
         * Ждём, чтобы убедиться что всё завершилось
         */
        Thread.sleep(15.seconds.inWholeMilliseconds)


        println("My program run ends...: ${Thread.currentThread().name}")
    }
}