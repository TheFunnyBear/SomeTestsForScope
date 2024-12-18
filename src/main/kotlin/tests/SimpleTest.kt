package org.example.tests

class SimpleTest() {

    fun start() {
        println("My test coroutine runs...:  ${Thread.currentThread().name}")



        println("My test run ends...: ${Thread.currentThread().name}")
    }
}