package org.example

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

class SimpleTest() {

    fun start() {
        println("My test coroutine runs...:  ${Thread.currentThread().name}")



        println("My test run ends...: ${Thread.currentThread().name}")
    }
}