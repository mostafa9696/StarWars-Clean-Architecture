package com.example.starwarscharacters.di

import com.example.data_remote.api.StarWarsAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideOkHttpClient() }

    single { provideRetrofit(okHttpClient = get(), baseUrl = "https://swapi.dev/api/") }

    single { getStarWarsApiService(retrofit = get()) }
}

internal fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

internal fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

internal fun getStarWarsApiService(retrofit: Retrofit): StarWarsAPIService =
    retrofit.create(StarWarsAPIService::class.java)