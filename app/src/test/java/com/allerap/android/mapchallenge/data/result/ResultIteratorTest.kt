package com.allerap.android.mapchallenge.data.result

import com.allerap.android.mapchallenge.data.result.Err
import com.allerap.android.mapchallenge.data.result.Ok
import com.allerap.android.mapchallenge.data.result.iterator
import com.allerap.android.mapchallenge.data.result.mutableIterator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ResultIteratorTest {

    @Test
    fun `hasNext returns true if unyielded and Ok`() {
        val iterator = Ok("hello").iterator()
        assertTrue { iterator.hasNext() }
    }

    @Test
    fun `hasNext returnsFalse If Err`() {
        val iterator = Err("hello").iterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun `hasNext returns false if yielded`() {
        val iterator = Ok("hello").iterator()
        iterator.next()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun `next returns value if unyielded and Ok`() {
        assertEquals(
                expected = "hello",
                actual = Ok("hello").iterator().next()
        )
    }

    @Test
    fun `next throws exception if unyielded and Err`() {
        val iterator = Err("hello").iterator()
        assertFailsWith<NoSuchElementException> { iterator.next() }
    }

    @Test
    fun `next throws exception if yielded and Ok`() {
        val iterator = Ok("hello").iterator()
        iterator.next()
        assertFailsWith<NoSuchElementException> { iterator.next() }
    }

    @Test
    fun `remove makes hasNext return false`() {
        val iterator = Ok("hello").mutableIterator()
        iterator.remove()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun `remove makesNext throw exception`() {
        val iterator = Ok("hello").mutableIterator()
        iterator.remove()
        assertFailsWith<NoSuchElementException> { iterator.next() }
    }
}
