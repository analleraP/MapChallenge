package com.allerap.android.mapchallenge.data.result

/**
 * Calls the specified function [block] with `value` as its argument if this [Result] is [Ok] and
 * returns and returns `this` value.
 */
inline infix fun <V, E> Result<V, E>.doOnSuccess(block: (V) -> Unit): Result<V, E> =
  when (this) {
    is Ok -> {
      block(value)
      this
    }
    is Err -> this
  }

/**
 * Calls the specified function [block] with `error` as its argument if this [Result] is [Err] and
 * returns and returns `this` value.
 */
inline infix fun <V, E> Result<V, E>.doOnFailure(block: (E) -> Unit): Result<V, E> =
  when (this) {
    is Ok -> this
    is Err -> {
      block(error)
      this
    }
  }
