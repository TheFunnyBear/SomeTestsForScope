package org.example

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

class CancelForGlobalScopeCoroutine() {

    @OptIn(DelicateCoroutinesApi::class)
    fun start() {
        println("My GlobalScope coroutine runs...:  ${Thread.currentThread().name}")

        /**
         * Глобальная область действия, не привязанная к какому-либо заданию.
         * Глобальная область действия используется для запуска корутн верхнего уровня,
         * которые работают в течение всего срока службы приложения и не отменяются преждевременно.
         */
        val globalScopeReporterJob = GlobalScope.launch {
            simpleCoroutine()
        }

        if (globalScopeReporterJob.isActive) {
            println("OK: Job is Active")
        } else {
            println("ERROR: Job is Inactive")
        }

        Thread.sleep(3.seconds.inWholeMilliseconds)
        println("Cancel started coroutine in GlobalScope")
        globalScopeReporterJob.cancel()

        Thread.sleep(3.seconds.inWholeMilliseconds)
        if (globalScopeReporterJob.isCancelled) {
            println("OK: Job is cancelled")
        } else {
            println("ERROR: Job is not cancelled!")
        }

        val isStarted = globalScopeReporterJob.start()
        if (!isStarted) {
            println("OK: Can't restart! Already started or computed or canceled")
        } else {
            println("ERROR: Wow it restarted :-)")
        }


        Thread.sleep(3.seconds.inWholeMilliseconds)
        println("My program run ends...: ${Thread.currentThread().name}")
    }

    private suspend fun simpleCoroutine(){
        for(i in 0..50){
            delay(500L)
            println(i)
        }

        println("Hello Coroutines")
    }
}