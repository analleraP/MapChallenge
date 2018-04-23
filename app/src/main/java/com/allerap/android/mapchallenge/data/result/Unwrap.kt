package com.allerap.android.mapchallenge.data.result

/**
 * Unwraps a [Result], yielding the [value][Ok.value].
 *
 * @throws UnwrapException if the [Result] is an [Err], with a message
 * containing the [error][Err.error].
 */
fun <V, E> Result<V, E>.unwrap(): V = when (this) {
    is Ok -> value
    is Err -> throw UnwrapException("called Result.wrap on an Err value $error")
}

/**
 * Unwraps a [Result], yielding the [value][Ok.value].
 *
 * @param message The message to include in the [UnwrapException] if the [Result] is an [Err].
 * @throws UnwrapException if the [Result] is an [Err], with the specified [message].
 */
inline infix fun <V, E> Result<V, E>.expect(message: () -> Any): V = when (this) {
    is Ok -> value
    is Err -> throw UnwrapException("${message()} $error")
}

/**
 * Unwraps a [Result], yielding the [error][Err.error].
 *
 * @throws UnwrapException if the [Result] is [Ok], with a message containing the [value][Ok.value].
 */
fun <V, E> Result<V, E>.unwrapError(): E = when (this) {
    is Ok -> throw UnwrapException("called Result.unwrapError on an Ok value $value")
    is Err -> error
}

/**
 * Unwraps a [Result], yielding the [error][Err.error].
 *
 * @param message The message to include in the [UnwrapException] if the [Result] is [Ok].
 * @throws UnwrapException if the [Result] is [Ok], with the specified [message].
 */
inline infix fun <V, E> Result<V, E>.expectError(message: () -> Any): E = when (this) {
    is Ok -> throw UnwrapException("${message()} $value")
    is Err -> error
}
