package org.example

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

class SimpleGlobalScopeCoroutine() {

    @OptIn(DelicateCoroutinesApi::class)
    fun start() {
        println("My GlobalScope coroutine runs...:  ${Thread.currentThread().name}")

        /**
         * Глобальная область действия, не привязанная к какому-либо заданию.
         * Глобальная область действия используется для запуска корутн верхнего уровня,
         * которые работают в течение всего срока службы приложения и не отменяются преждевременно.
         */
        GlobalScope.launch {
            simpleCoroutine()
        }


        /**
         * Если тут не ждать, то приложение закончит работу не дождавшись
         * результата работы корутины.
         */

        Thread.sleep(10.seconds.inWholeMilliseconds)
        println("My program run ends...: ${Thread.currentThread().name}")
    }

    private suspend fun simpleCoroutine(){
        for(i in 0..5){
            delay(500L)
            println(i)
        }

        println("Hello Coroutines")
    }
}