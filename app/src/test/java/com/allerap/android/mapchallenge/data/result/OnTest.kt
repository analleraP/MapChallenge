package com.allerap.android.mapchallenge.data.result

import kotlin.test.Test
import kotlin.test.assertEquals

class OnTest {

    object CounterError
    class Counter(var count: Int)

    @Test
    fun `onSuccess invokes callback if Ok`() {
        val counter = Counter(50)

        Ok(counter).onSuccess { it.count += 50 }

        assertEquals(
                expected = 100,
                actual = counter.count
        )
    }

    @Test
    fun `onSuccess invokes nothing if Err`() {
        val counter = Counter(200)

        Err(CounterError).onSuccess { counter.count -= 50 }

        assertEquals(
                expected = 200,
                actual = counter.count
        )
    }

    @Test
    fun `onFailure invokes callback if Err`() {
        val counter = Counter(555)

        Err(CounterError).onFailure { counter.count += 100 }

        assertEquals(
                expected = 655,
                actual = counter.count
        )
    }

    @Test
    fun `onFailure invokes nothing if Ok`() {
        val counter = Counter(1020)

        Ok("hello").onFailure { counter.count = 1030 }

        assertEquals(
                expected = 1020,
                actual = counter.count
        )
    }
}
