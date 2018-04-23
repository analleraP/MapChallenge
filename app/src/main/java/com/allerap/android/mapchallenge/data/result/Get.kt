package com.allerap.android.mapchallenge.data.result

/**
 * Returns the [value][Ok.value] if this [Result] is [Ok], otherwise `null`.
 */
fun <V, E> Result<V, E>.get(): V? = when (this) {
  is Ok -> value
  is Err -> null
}

/**
 * Returns the [error][Err.error] if this [Result] is [Err], otherwise `null`.
 */
fun <V, E> Result<V, E>.getError(): E? = when (this) {
  is Ok -> null
  is Err -> error
}

/**
 * Returns the [value][Ok.value] if this [Result] is [Ok], otherwise [default].
 *
 * @param default The value to return if [Err].
 * @return The [value][Ok.value] if [Ok], otherwise [default].
 */
inline infix fun <V, E> Result<V, E>.getOr(default: () -> V): V = when (this) {
  is Ok -> value
  is Err -> default()
}

/**
 * Returns the [error][Err.error] if this [Result] is [Err], otherwise [default].
 *
 * - Haskell: [Result.fromRight](https://hackage.haskell.org/package/base-4.10.0.0/docs/Data-Either.html#v:fromRight)
 *
 * @param default The error to return if [Ok].
 * @return The [error][Err.error] if [Err], otherwise [default].
 */
inline infix fun <V, E> Result<V, E>.getErrorOr(default: () -> E): E = when (this) {
  is Ok -> default()
  is Err -> error
}

/**
 * Returns the [value][Ok.value] if this [Result] is [Ok], otherwise
 * the [transformation][transform] of the [error][Err.error].
 *
 * - Elm: [Result.extract](http://package.elm-lang.org/packages/circuithub/elm-result-extra/1.4.0/Result-Extra#extract)
 * - Rust: [Result.unwrap_or_else](https://doc.rust-lang.org/src/core/result.rs.html#735-740)
 */
inline infix fun <V, E> Result<V, E>.getOrElse(transform: (E) -> V): V = when (this) {
  is Ok -> value
  is Err -> transform(error)
}

/**
 * Returns the [error][Err.error] if this [Result] is [Err], otherwise
 * the [transformation][transform] of the [value][Ok.value].
 */
inline infix fun <V, E> Result<V, E>.getErrorOrElse(transform: (V) -> E): E = when (this) {
  is Ok -> transform(value)
  is Err -> error
}
