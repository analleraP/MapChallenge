package com.allerap.android.mapchallenge.data.result

import kotlin.test.Test
import kotlin.test.assertEquals

class OrTest {

    private object OrError

    @Test
    fun `or returns value if Ok`() {
        assertEquals(
                expected = 500,
                actual = Ok(500).or { Ok(1000) }.get()
        )
    }

    @Test
    fun `or returns default value if Err`() {
        assertEquals(
                expected = 5000,
                actual = Err(OrError).or { Ok(5000) }.get()
        )
    }

    @Test
    fun `orElse returns value if Ok`() {
        assertEquals(
                expected = 3000,
                actual = Ok(3000).orElse { Ok(4000) }.get()
        )
    }

    @Test
    fun `orElse returns transformed value if Err`() {
        assertEquals(
                expected = 2000,
                actual = Err(4000).orElse { Ok(2000) }.get()
        )
    }

}