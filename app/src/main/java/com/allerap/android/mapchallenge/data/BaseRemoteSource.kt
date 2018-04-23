package com.allerap.android.mapchallenge.data

import android.util.Log
import com.allerap.android.mapchallenge.data.entities.ErrorEntity
import com.allerap.android.mapchallenge.data.result.Err
import com.allerap.android.mapchallenge.data.result.Ok
import com.allerap.android.mapchallenge.data.result.Result
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.IOException
import com.allerap.android.mapchallenge.domain.entities.Error

open class BaseRemoteSource(private val moshi: Moshi) {

    internal fun <T> call(call: Call<T>): Result<T, Error> = try {

        val response = call.execute()

        if (response.isSuccessful && response.body() != null) {
            // Ignore the !!. A NPE will never be thrown here.
            Ok(response.body()!!)
        } else {
            Err(parseBody(response.errorBody()))
        }
    } catch (e: IOException) {
        Log.e("BaseRemoteSource", e.localizedMessage)
        Err(Error("Network error"))
    } catch (e: Exception) {
        Log.e("BaseRemoteSource", e.localizedMessage)
        Err(Error("InternalServerDramaError"))
    }

    // Parses the [errorBody] [ResponseBody] to an [Error].
    private fun parseBody(errorBody: ResponseBody?): Error = if (errorBody != null) {
        try {
            moshi.adapter(ErrorEntity::class.java).fromJson(errorBody.string())?.let {
                Error(it.message)
            } ?: run {
                Error("InternalServerDramaError")
            }
        } catch (e: Exception) {
            Log.e("BaseRemoteSource", e.localizedMessage)
            Error("InternalServerDramaError")
        }
    } else {
        Error("InternalServerDramaError")
    }
}
