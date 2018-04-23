package com.allerap.android.mapchallenge.data.result

/**
 * Returns [result] if this [Result] is [Err], otherwise this [Ok].
 */
@Suppress("FunctionMinLength")
inline infix fun <V, E> Result<V, E>.or(result: () -> Result<V, E>): Result<V, E> = when (this) {
    is Ok -> this
    is Err -> result()
}

/**
 * Returns the [transformation][transform] of the [error][Err.error] if this [Result] is [Err],
 * otherwise this [Ok].
 */
inline infix fun <V, E> Result<V, E>.orElse(transform: (E) -> Result<V, E>): Result<V, E> =
        when (this) {
            is Ok -> this
            is Err -> transform(error)
        }
