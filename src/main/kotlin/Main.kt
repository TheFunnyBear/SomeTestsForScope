package org.example

import org.example.tests.*

fun main() {
    println("Hello World!")

    val test = TestsTypes.RUN_MANY_SCOPES

    when (test) {
        TestsTypes.SIMPLE_THREAD -> {
            val simpleThreadStart = SimpleThreadStart()
            simpleThreadStart.start()
        }
        TestsTypes.MAX_THREADS -> {
            val maxThreadStart = MaxThreadStart()
            maxThreadStart.start()
        }

        TestsTypes.SIMPLE_GLOBAL_SCOPE_COROUTINE -> {
            val simpleGlobalScopeCoroutine = SimpleGlobalScopeCoroutine()
            simpleGlobalScopeCoroutine.start()
        }

        TestsTypes.EXCEPTION_IN_GLOBAL_SCOPE_COROUTINE -> {
            val exceptionInGlobalScopeCoroutine = ExceptionInGlobalScopeCoroutine()
            exceptionInGlobalScopeCoroutine.start()
        }

        TestsTypes.CANCEL_FOR_GLOBAL_SCOPE_COROUTINE -> {
            val cancelForGlobalScopeCoroutine = CancelForGlobalScopeCoroutine()
            cancelForGlobalScopeCoroutine.start()
        }

        TestsTypes.DEFAULT_DISPATCHER_COROUTINE -> {
            val defaultDispatcherCoroutine = DefaultDispatcherCoroutine()
            defaultDispatcherCoroutine.start()
        }

        TestsTypes.IO_DISPATCHER_COROUTINE -> {
            val ioDispatcherCoroutine = IODispatcherCoroutine()
            ioDispatcherCoroutine.start()
        }

        TestsTypes.DISPATCHERS_COROUTINE -> {
            val dispatchersCoroutine = DispatchersCoroutine()
            dispatchersCoroutine.start()
        }

        TestsTypes.UNCONFINED_DISPATCHER_COROUTINE -> {
            val unconfinedDispatcherCoroutine = UnconfinedDispatcherCoroutine()
            unconfinedDispatcherCoroutine.start()
        }

        TestsTypes.DEAD_LOCK_IN_COROUTINE -> {
            val deadLockInCoroutines = DeadLockInCoroutines()
            deadLockInCoroutines.start()
        }

        TestsTypes.RUN_MANY_SCOPES -> {
            val runManyScopesTest = RunManyScopesTest()
            runManyScopesTest.start()
        }

    }

}

enum class TestsTypes {
    SIMPLE_THREAD,
    MAX_THREADS,
    SIMPLE_GLOBAL_SCOPE_COROUTINE,
    EXCEPTION_IN_GLOBAL_SCOPE_COROUTINE,
    CANCEL_FOR_GLOBAL_SCOPE_COROUTINE,
    DEFAULT_DISPATCHER_COROUTINE,
    IO_DISPATCHER_COROUTINE,
    DISPATCHERS_COROUTINE,
    UNCONFINED_DISPATCHER_COROUTINE,
    DEAD_LOCK_IN_COROUTINE,
    RUN_MANY_SCOPES,
}

