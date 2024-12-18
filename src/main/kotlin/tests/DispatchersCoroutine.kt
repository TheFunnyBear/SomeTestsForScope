package org.example.tests

import kotlinx.coroutines.*
import org.example.Payload
import kotlin.time.Duration.Companion.seconds

class DispatchersCoroutine() {

    fun start() {
        println("My GlobalScope coroutine runs...:  ${Thread.currentThread().name}")

        val defaultScope = CoroutineScope(Dispatchers.Default)
        val ioScope = CoroutineScope(Dispatchers.IO)

        /**
         * Чтобы работать с Main диспетчером на JVM, в зависимости от среды выполнения
         * проекта необходимо добавить следующий артефакт:
         *
         *     kotlinx-coroutines-android — для диспетчера основного потока Android
         *     kotlinx-coroutines-javafx — для диспетчера потоков приложений JavaFX
         *     kotlinx-coroutines-swing —  для диспетчера Swing EDT
         *
         *     Для консольных приложений не применимо
         */
        //val mainScope = CoroutineScope(Dispatchers.Main)
        val unconfinedScope = CoroutineScope(Dispatchers.Unconfined)

        val scopes = listOf(defaultScope, ioScope, /*mainScope,*/ unconfinedScope)

        scopes.forEach { scope ->
            scope.launch {
                println("Launched in: $scope coroutine")
                Payload.doSomeCalc(repeat = 50, eatMemory = false)
                println("End in $scope coroutine")
            }
        }


        Thread.sleep(10.seconds.inWholeMilliseconds)
        println("My program run ends...: ${Thread.currentThread().name}")
    }
}