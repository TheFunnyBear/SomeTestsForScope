package org.example.tests

import kotlinx.coroutines.*
import org.example.Payload
import kotlin.time.Duration.Companion.seconds

class RunManyScopesTest() {

    fun start() {
        println("My test coroutine runs...:  ${Thread.currentThread().name}")

        val createdScopes = mutableListOf<CoroutineScope>()
        val startedJobs = mutableListOf<Job>()

        (0..100).forEach { index ->
            val dispatcherName = "Test Dispatcher number $index"
            val myLimitedDispatcher = Dispatchers.IO.limitedParallelism(
                100, name = dispatcherName
            )

            val myLimitedDispatcherScope = CoroutineScope(myLimitedDispatcher)
            createdScopes.add(myLimitedDispatcherScope)
        }
        println("All dispatchers created!")
        Thread.sleep(3.seconds.inWholeMilliseconds)

        createdScopes.forEach { myLimitedDispatcherScope ->
            (0..100).forEach { index ->
                val createdJob = myLimitedDispatcherScope.launch {
                    Payload.doSomeCalc(repeat = index, eatMemory = false)
                }
                startedJobs.add(createdJob)
            }
            println("All coroutines started in ${myLimitedDispatcherScope.coroutineContext}")
        }
        println("All coroutines started in all dispatchers!")

        do {
            val activeJobCount = startedJobs.count { job -> job.isActive }
            println("Active job count: $activeJobCount")
            Thread.sleep(5.seconds.inWholeMilliseconds)
        } while (activeJobCount > 0)

        println("Cancel all scopes!")
        createdScopes.forEach { it.cancel() }

        println("My test run ends...: ${Thread.currentThread().name}")
    }
}