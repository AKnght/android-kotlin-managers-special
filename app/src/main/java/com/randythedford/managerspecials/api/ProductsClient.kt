package com.randythedford.managerspecials.api

import com.randythedford.managerspecials.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductsClient {

    private const val PROD_SERVER = "https://raw.githubusercontent.com"
    private const val DEBUG = "debug";

    private val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level

        if (BuildConfig.BUILD_TYPE.contentEquals(DEBUG)) {
            levelType = Level.BODY
        }
        else {
            levelType = Level.NONE
        }

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(PROD_SERVER)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiProductsInterface: ProductsApiInterface by lazy {
        retrofitClient
            .build()
            .create(ProductsApiInterface::class.java)
    }
}