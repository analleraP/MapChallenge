package com.allerap.android.mapchallenge.data.result

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetTest {

    @Test
    fun `get returns value if Ok`() {
        assertEquals(
                expected = 12,
                actual = Ok(12).get()
        )
    }

    @Test
    fun `get returns null if Err`() {
        assertNull(Err("error").get())
    }

    @Test
    fun `getError returns null if Ok`() {
        assertNull(Ok("example").getError())
    }

    @Test
    fun `getError returns error if Err`() {
        assertEquals(
                expected = "example",
                actual = Err("example").getError()
        )
    }

    @Test
    fun `getOr returns value if Ok`() {
        assertEquals(
                expected = "hello",
                actual = Ok("hello").getOr { "world" }
        )
    }

    @Test
    fun `getOr returns default value if Err`() {
        assertEquals(
                expected = "default",
                actual = Err("error").getOr { "default" }
        )
    }

    @Test
    fun `getErrorOr returns default value if Ok`() {
        assertEquals(
                expected = "world",
                actual = Ok("hello").getErrorOr { "world" }
        )
    }

    @Test
    fun `getErrorOr returns error if Err`() {
        assertEquals(
                expected = "hello",
                actual = Err("hello").getErrorOr { "world" }
        )
    }

    @Test
    fun `getOrElse returns value if Ok`() {
        assertEquals(
                expected = "hello",
                actual = Ok("hello").getOrElse { "world" }
        )
    }

    @Test
    fun `getOrElse returns transformed error if Err`() {
        assertEquals(
                expected = "world",
                actual = Err("hello").getOrElse { "world" }
        )
    }

    @Test
    fun `getErrorOrElse returns transformed value if Ok`() {
        assertEquals(
                expected = "world",
                actual = Ok("hello").getErrorOrElse { "world" }
        )
    }

    @Test
    fun `getErrorOrElse returns error if Err`() {
        assertEquals(
                expected = "hello",
                actual = Err("hello").getErrorOrElse { "world" }
        )
    }

}