package com.allerap.android.mapchallenge.data.result

/**
 * Maps this [Result<V, E>][Result] to [Result<U, E>][Result] by either applying the [transform]
 * function to the [value][Ok.value] if this [Result] is [Ok], or returning this [Err].
 */
inline infix fun <V, E, U> Result<V, E>.map(transform: (V) -> U): Result<U, E> = when (this) {
  is Ok -> Ok(transform(value))
  is Err -> this
}

/**
 * Maps this [Result<V, E>][Result] to [Result<V, F>][Result] by either applying the [transform]
 * function to the [error][Err.error] if this [Result] is [Err], or returning this [Ok].
 */
inline infix fun <V, E, F> Result<V, E>.mapError(transform: (E) -> F): Result<V, F> = when (this) {
  is Ok -> this
  is Err -> Err(transform(error))
}

/**
 * Maps this [Result<V, E>][Result] to `U` by applying either the [success] function if this [Result]
 * is [Ok], or the [failure] function if this [Result] is an [Err]. Both of these functions must
 * return the same type (`U`).
 */
inline fun <V, E, U> Result<V, E>.mapBoth(
    success: (V) -> U,
    failure: (E) -> U
): U = when (this) {
  is Ok -> success(value)
  is Err -> failure(error)
}

/**
 * Maps this [Result<V, E>][Result] to [Result<U, F>][Result] by applying either the [success] function
 * if this [Result] is [Ok], or the [failure] function if this [Result] is an [Err].
 */
inline fun <V, E, U, F> Result<V, E>.mapEither(
    success: (V) -> U,
    failure: (E) -> F
): Result<U, F> = when (this) {
  is Ok -> Ok(success(value))
  is Err -> Err(failure(error))
}

/**
 * Maps this [Result<V, E>][Result] to [Result<U, E>][Result] by either applying the [transform] function
 * if this [Result] is [Ok], or returning this [Err].
 *
 * This is functionally equivalent to [andThen].
 */
inline infix fun <V, E, U> Result<V, E>.flatMap(transform: (V) -> Result<U, E>): Result<U, E> =
  andThen(transform)

