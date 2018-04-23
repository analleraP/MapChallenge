package com.allerap.android.mapchallenge.feature

import com.allerap.android.mapchallenge.CoroutineContextPool
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

open class TestCoroutineContextPool() : CoroutineContextPool() {
  override val ui: CoroutineContext by lazy { Unconfined }
  override val bg: CoroutineContext by lazy { Unconfined }
}
