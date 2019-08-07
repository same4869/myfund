package com.xun.myfund.net

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.xun.myfund.app.FundApplication
import com.xun.myfund.utils.LogUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor(baseUrl: String) {
    private var httpCacheDirectory: File? = null
    private var cache: Cache? = null
    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit?
    private val DEFAULT_TIMEOUT: Long = 15
    private val url = baseUrl

    init {
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(FundApplication.getAppContext()?.cacheDir, "app_cache")
        }
        if (cache == null) {
            cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
        }

        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            LogUtils.e("OKHttp-----", message)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .cache(cache)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .build()
        retrofit = Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .build()

    }

    companion object {
        private var instance: RetrofitClient? = null

        fun getInstance(baseUrl: String): RetrofitClient {
            if (instance == null) {
                synchronized(RetrofitClient::class) {
                    if (instance == null) {
                        instance = RetrofitClient(baseUrl)
                    }
                }
            }
            return instance!!
        }

        fun getApiServiceWithBaseUrl(baseUrl: String): ApiService {
            val retrofitClient = getInstance(baseUrl)
            return retrofitClient.create(ApiService::class.java)
        }

    }

    fun <T> create(service: Class<T>?): T {
        if (service == null || retrofit == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit!!.create(service)
    }

}