package com.allerap.android.mapchallenge.data.result

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class UnwrapTest {

    @Test
    fun `unwrap returns value if Ok`() {
        assertEquals(
                expected = 5000,
                actual = Ok(5000).unwrap()
        )
    }

    @Test
    fun `unwrap throws exception if Err`() {
        assertFailsWith<UnwrapException>("called Result.wrap on an Err value 5000") {
            Err(5000).unwrap()
        }
    }

    @Test
    internal fun `expect returns value if Ok`() {
        assertEquals(
                expected = 1994,
                actual = Ok(1994).expect { "the year should be" }
        )
    }

    @Test
    fun `expect throws exception if Err`() {
        val message = object {
            override fun toString() = "the year should be"
        }

        assertFailsWith<UnwrapException>("the year should be 1994") {
            Err(1994).expect { message }
        }
    }

    @Test
    fun `unwrapError throws exception if Ok`() {
        assertFailsWith<UnwrapException>("called Result.unwrapError on an Ok value example") {
            Ok("example").unwrapError()
        }
    }

    @Test
    fun `unwrapError returns error if Err`() {
        assertEquals(
                expected = "example",
                actual = Err("example").unwrapError()
        )
    }

    @Test
    fun `expectError throws exception if Ok`() {
        val message = object {
            override fun toString() = "the year should be"
        }

        assertFailsWith<UnwrapException>("the year should be 2010") {
            Ok(2010).expectError { message }
        }
    }

    @Test
    fun `expectError returns error if Err`() {
        assertEquals(
                expected = 2010,
                actual = Err(2010).expectError { "the year should be" }
        )
    }
}
