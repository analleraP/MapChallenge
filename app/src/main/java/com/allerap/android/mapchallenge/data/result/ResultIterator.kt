package com.allerap.android.mapchallenge.data.result

/**
 * Returns an [Iterator] over the possibly contained [value][Ok.value].
 * The iterator yields one [value][Ok.value] if the [Result] is [Ok],
 * otherwise throws [NoSuchElementException].
 */
fun <V, E> Result<V, E>.iterator(): Iterator<V> = ResultIterator(this)

/**
 * Returns a [MutableIterator] over the possibly contained [value][Ok.value].
 * The iterator yields one [value][Ok.value] if the [Result] is [Ok],
 * otherwise throws [NoSuchElementException].
 */
fun <V, E> Result<V, E>.mutableIterator(): MutableIterator<V> = ResultIterator(this)

private class ResultIterator<out V, out E>(private val result: Result<V, E>) : MutableIterator<V> {

  /**
   * A flag indicating whether this [Iterator] has [yielded] its [Result].
   */
  private var yielded = false

  /**
   * @return `true` if the [value][Ok.value] is not [yielded] and [Ok], `false` otherwise.
   */
  override fun hasNext(): Boolean {
    if (yielded) {
      return false
    }

    return when (result) {
      is Ok -> true
      is Err -> false
    }
  }

  /**
   * Returns the [Result's][Result] [value][Ok.value] if not [yielded] and [Ok].
   * @throws NoSuchElementException if the [Result] is [yielded] or is not [Ok].
   */
  override fun next(): V {
    if (!yielded && result is Ok) {
      yielded = true
      return result.value
    } else {
      throw NoSuchElementException()
    }
  }

  /**
   * Flags this [Iterator] as having [yielded] its [Result].
   */
  override fun remove() {
    yielded = true
  }
}
