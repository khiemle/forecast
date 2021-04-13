package com.khiemle.data.deps

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

internal const val OPEN_WEATHER_RETROFIT = "OpenWeatherRetrofit"
internal const val OPEN_WEATHER_OKHTTP = "OpenWeatherOkHttp"
internal const val OPEN_WEATHER_BASE_URL = "OpenWeatherBaseUrl"

@Module
internal class NetworkModule {
    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Named(OPEN_WEATHER_BASE_URL)
    fun provideBaseUrl(): String = "https://api.openweathermap.org"

    @Provides
    @Named(OPEN_WEATHER_OKHTTP)
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Named(OPEN_WEATHER_RETROFIT)
    fun provideRetrofit(
        okHttpClient: OkHttpClient, @Named(OPEN_WEATHER_BASE_URL) baseUrl: String,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}