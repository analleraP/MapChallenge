package com.allerap.android.mapchallenge.data.result

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class MapTest {

    private sealed class MapError(val reason: String) {
        object HelloError : MapError("hello")
        object WorldError : MapError("world")
        class CustomError(reason: String) : MapError(reason)
    }

    @Test
    fun `map returns transformed value if Ok`() {
        assertEquals(
                expected = 30,
                actual = Ok(10).map { it + 20 }.get()
        )
    }

    @Test
    @Suppress("UNREACHABLE_CODE")
    fun `map returns error if Err`() {
        val result = Err(MapError.HelloError).map { "hello $it" }

        result as Err

        assertSame(
                expected = MapError.HelloError,
                actual = result.error
        )
    }

    @Test
    fun `mapError returns value if Ok`() {
        val value = Ok(55).map { it + 15 }
                .mapError { MapError.WorldError }
                .get()

        assertEquals(
                expected = 70,
                actual = value
        )
    }

    @Test
    fun `mapError returns error if Err`() {
        val result: Result<String, MapError> = Ok("let")
                .map { "$it me" }
                .andThen {
                    when (it) {
                        "let me" -> Err(MapError.CustomError("$it $it"))
                        else -> Ok("$it get")
                    }
                }
                .mapError { MapError.CustomError("${it.reason} get what i want") }

        result as Err

        assertEquals(
                expected = "let me let me get what i want",
                actual = result.error.reason
        )
    }

    @Test
    @Suppress("UNREACHABLE_CODE")
    fun `mapBoth returns transformed value if Ok`() {
        val value = Ok("there is").mapBoth(
                success = { "$it a light" },
                failure = { "$it that never" }
        )

        assertEquals(
                expected = "there is a light",
                actual = value
        )
    }

    @Test
    @Suppress("UNREACHABLE_CODE")
    fun `mapBoth returns transformed error if Err`() {
        val error = Err(MapError.CustomError("this")).mapBoth(
                success = { "$it charming" },
                failure = { "${it.reason} man" }
        )

        assertEquals(
                expected = "this man",
                actual = error
        )
    }

    @Test
    @Suppress("UNREACHABLE_CODE")
    fun `mapEither returns transformed value if Ok`() {
        val result = Ok(500).mapEither(
                success = { it + 500 },
                failure = { MapError.CustomError("$it") }
        )

        result as Ok

        assertEquals(
                expected = 1000,
                actual = result.value
        )
    }

    @Test
    fun `mapEither returns transformed error if Err`() {
        val result = Err("the reckless").mapEither(
                success = { "the wild youth" },
                failure = { MapError.CustomError("the truth") }
        )

        result as Err

        assertEquals(
                expected = "the truth",
                actual = result.error.reason
        )
    }
}
