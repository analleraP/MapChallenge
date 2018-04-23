package com.allerap.android.mapchallenge.data.result

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class ResultTest {

    @Test
    fun `of returns Ok if invocation successful`() {
        val callback = { "example" }
        val result = Result.of(callback)

        assertEquals(
                expected = "example",
                actual = result.get()
        )
    }

    @Test
    fun `of returns Err if invocation fails`() {
        val exception = IllegalArgumentException("throw me")
        val callback = { throw exception }
        val result = Result.of(callback)

        assertSame(
                expected = exception,
                actual = result.getError()
        )
    }

    @Test
    fun `toResultOr returns Ok if non null`() {
        val result = "ok".toResultOr { "err" }

        assertEquals(
                expected = "ok",
                actual = result.get()
        )
    }

    @Test
    internal fun `toResultOr returns Err if null`() {
        val result = "ok".toLongOrNull()
                .toResultOr { "err" }

        assertEquals(
                expected = "err",
                actual = result.getError()
        )
    }
}
