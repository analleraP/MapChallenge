package com.allerap.android.mapchallenge.data.result

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class AndTest {

    private object AndError

    @Test
    fun `and returns value if Ok`() {
        assertEquals(
                expected = 500,
                actual = Ok(230).and { Ok(500) }.get()
        )
    }

    @Test
    fun `and returns value if Err`() {
        assertEquals(
                expected = "hello world",
                actual = Ok(300).and { Err("hello world") }.getError()
        )
    }

    @Test
    fun `andThen returns value if Ok`() {
        assertEquals(
                expected = 12,
                actual = Ok(5).andThen { Ok(it + 7) }.get()
        )
    }

    @Test
    fun `andThen returns error if Err`() {
        assertSame(
                expected = AndError,
                actual = Ok(20).andThen { Ok(it + 43) }.andThen { Err(AndError) }.getError()!!
        )
    }

}


