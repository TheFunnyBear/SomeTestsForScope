package org.example

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

class IODispatcherCoroutine() {

    fun start() {
        println("My GlobalScope coroutine runs...:  ${Thread.currentThread().name}")

        /**
         * CoroutineScope (Dispatchers.IO)
         * в системе может быть до 64 + 100 + 60 потоков, предназначенных для блокировки
         * задач во время пиковых нагрузок, но в стационарном режиме диспетчеры
         * совместно используют только небольшое количество потоков.IO,
         * myMysqlDbDispatcher и myMongoDbDispatcher
         */
        val scope = CoroutineScope(Dispatchers.IO)

        var launchedCount = 0
        (0..1000).forEach { index ->
            println("Start index: $index coroutine in IO scope ")
            scope.launch {
                println("Launched index: $index coroutine in IO scope, launched count ${launchedCount++} ")
                Payload.doSomeCalc(repeat = index, eatMemory = false)
                println("End $index coroutine in IO scope ")
                launchedCount--
            }
        }

        /**
         * Видно, что корутины стартуют сразу, но их запуск происходит не сразу.
         * Одновременно живёт максимальное количество потоков, используемых этим диспетчером,
         * равно количеству ядер процессора
         */


        Thread.sleep(10.seconds.inWholeMilliseconds)


        /**
         * Отменяем все корутины, которые работают и стартовали, но не запущенны.
         * Которые работают должны доработать.
         */
        scope.cancel()
        println("Scope canceled!")

        /**
         * Ждём, чтобы убедиться что всё завершилось
         */
        Thread.sleep(5.seconds.inWholeMilliseconds)


        // 100 threads for MySQL connection
        val myMysqlDbDispatcher = Dispatchers.IO.limitedParallelism(
            100)

        val mySqlDispatcher = CoroutineScope(myMysqlDbDispatcher)
        var launchedMySQlCount = 0
        (0..1000).forEach { index ->
            println("Start index: $index coroutine in IO scope with limitedParallelism")
            mySqlDispatcher.launch {
                println("Launched index: $index coroutine in IO scope with limitedParallelism, launched count ${launchedMySQlCount++} ")
                Payload.doSomeCalc(repeat = index, eatMemory = false)
                println("End $index coroutine in IO scope with limitedParallelism ")
                launchedMySQlCount--
            }
        }
        Thread.sleep(20.seconds.inWholeMilliseconds)

        mySqlDispatcher.cancel()
        println("Scope canceled! with limitedParallelism")

        Thread.sleep(5.seconds.inWholeMilliseconds)

        println("My program run ends...: ${Thread.currentThread().name}")
    }
}