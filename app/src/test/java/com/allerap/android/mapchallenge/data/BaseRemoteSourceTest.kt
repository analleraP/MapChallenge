package com.allerap.android.mapchallenge.data

import com.allerap.android.mapchallenge.BuildConfig
import com.allerap.android.mapchallenge.data.mappers.RateMapper
import com.allerap.android.mapchallenge.data.mappers.StopMapper
import com.allerap.android.mapchallenge.data.mappers.VehicleMapper
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.apache.commons.io.FileUtils
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.io.IOException
import java.util.Locale
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

open class BaseRemoteSourceTest {

    val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    val stopMapper = StopMapper()
    val rateMapper = RateMapper(VehicleMapper())
    private val server = MockWebServer()
    private val authInterceptor: AuthInterceptor = AuthInterceptor()
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
                    })
            .build()
    private lateinit var retrofit: Retrofit

    @BeforeTest
    open fun setUp() {
        Locale.setDefault(Locale("en", "EN"))
        // Start the server.
        server.start()
        // Retrofit needs to be initialized after the server starts.
        retrofit = Builder()
                .baseUrl(server.url("/").toString())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
    }

    @AfterTest
    @Throws(IOException::class)
    open fun tearDown() {
        // Shut down the server. Instances cannot be reused.
        server.shutdown()
    }

    fun <T> create(service: Class<T>): T = retrofit.create(service)

    @Throws(IOException::class)
    fun enqueueMockResponse(code: Int) {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(code)
        server.enqueue(mockResponse)
    }

    @Throws(IOException::class)
    fun enqueueMockResponse(
            code: Int,
            responseFileName: String
    ) {
        val mockResponse = MockResponse()
        val json = getJsonFrom(responseFileName)

        mockResponse.setResponseCode(code)
        mockResponse.setBody(json)

        server.enqueue(mockResponse)
    }

    @Throws(InterruptedException::class)
    fun assertRequestSentTo(url: String) {
        val request = server.takeRequest()
        assertEquals(url, request.path)
    }

    @Throws(InterruptedException::class)
    fun assertGetRequestSentTo(url: String) {
        val request = server.takeRequest()
        assertEquals(url, request.path)
        assertEquals("GET", request.method)
    }

    @Throws(InterruptedException::class)
    fun assertPostRequestSentTo(url: String) {
        val request = server.takeRequest()
        assertEquals(url, request.path)
        assertEquals("POST", request.method)
    }

    @Throws(InterruptedException::class)
    fun assertPutRequestSentTo(url: String) {
        val request = server.takeRequest()
        assertEquals(url, request.path)
        assertEquals("PUT", request.method)
    }

    @Throws(InterruptedException::class)
    fun assertDeleteRequestSentTo(url: String) {
        val request = server.takeRequest()
        assertEquals(url, request.path)
        assertEquals("DELETE", request.method)
    }

    @Throws(InterruptedException::class)
    fun assertRequestContainsHeader(
            key: String,
            expectedValue: String
    ) {
        val recordedRequest = server.takeRequest()
        val value = recordedRequest.getHeader(key)
        assertEquals(expectedValue, value)
    }

    @Throws(InterruptedException::class, IOException::class)
    fun assertRequestBodyEquals(fileName: String) {
        val request = server.takeRequest()
        val expectedContent = getJsonFrom(fileName)
        val requestBody = request.body.readUtf8()
        assertEquals(expectedContent, requestBody)
    }

    @Throws(IOException::class)
    private fun getJsonFrom(name: String): String {
        val fileName = javaClass.getResource("/$name")
                .file
        val file = File(fileName)
        val lines = FileUtils.readLines(file, "UTF-8")
        val stringBuilder = StringBuilder()
        for (line in lines) {
            stringBuilder.append(line)
        }
        return stringBuilder.toString()
    }
}
