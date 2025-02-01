package com.example.posttemplate.data.di

import android.content.Context
import com.example.posttemplate.data.BuildConfig
import com.example.posttemplate.data.local.AppDatabase
import com.example.posttemplate.data.local.PostDao
import com.example.posttemplate.data.local.UserDao
import com.example.posttemplate.data.local.create
import com.example.posttemplate.data.remote.ApiService
import com.example.posttemplate.data.remote.NetworkClient
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

val dataModule = module {

    single<Cache> {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val cacheDir = File(androidApplication().cacheDir, "http_cache")
        if(!cacheDir.exists()) cacheDir.mkdirs()
        Cache(cacheDir, cacheSize)
    }

    single<OkHttpClient> {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        OkHttpClient.Builder()
            .cache(get())
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single {
        androidContext().getSharedPreferences("auth_preferences", Context.MODE_PRIVATE)
    }

    single<AppDatabase> { AppDatabase.create() }

    single<PostDao> { get<AppDatabase>().postDao() }
    single<UserDao> { get<AppDatabase>().userDao() }
}
