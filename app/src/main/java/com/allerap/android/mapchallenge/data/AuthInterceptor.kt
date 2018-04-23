package com.allerap.android.mapchallenge.data

import com.allerap.android.mapchallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor : Interceptor {

  override fun intercept(chain: Chain): Response {

    val token = BuildConfig.AUTH_TOKEN
    var request = chain.request()

    if (!token.isNullOrEmpty()) {
      request = request.newBuilder()
          .addHeader("Authorization", "Bearer $token")
          .build()
    }

    return chain.proceed(request)
  }

}
