package com.allerap.android.mapchallenge.data.result

/**
 * Returns [result] if this [Result] is [Ok], otherwise this [Err].
 */
inline infix fun <V, E> Result<V, E>.and(result: () -> Result<V, E>): Result<V, E> = when (this) {
    is Ok -> result()
    is Err -> this
}

/**
 * Maps this [Result<V, E>][Result] to [Result<U, E>][Result] by either applying the [transform]
 * function if this [Result] is [Ok], or returning this [Err].
 */
inline infix fun <V, E, U> Result<V, E>.andThen(transform: (V) -> Result<U, E>): Result<U, E> =
        when (this) {
            is Ok -> transform(value)
            is Err -> this
        }
