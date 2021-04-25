package com.khiemle.data.deps

import com.google.gson.Gson
import com.khiemle.data.remote.IOpenWeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

internal const val OPEN_WEATHER_RETROFIT = "OpenWeatherRetrofit"
internal const val OPEN_WEATHER_OKHTTP = "OpenWeatherOkHttp"
internal const val OPEN_WEATHER_BASE_URL = "OpenWeatherBaseUrl"
internal const val OPEN_WEATHER_APP_ID = "OpenWeatherAppId"

@Module
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    @Named(OPEN_WEATHER_BASE_URL)
    fun provideBaseUrl(appConfigs: AppConfigs): String = appConfigs.baseUrl


    @Provides
    @Singleton
    @Named(OPEN_WEATHER_APP_ID)
    fun provideAppId(appConfigs: AppConfigs): String = appConfigs.appId

    @Provides
    @Singleton
    @Named(OPEN_WEATHER_OKHTTP)
    fun provideOkHttp(appConfigs: AppConfigs, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .certificatePinner(
                certificatePinner = CertificatePinner.Builder()
                    .add(appConfigs.openWeatherDomain, appConfigs.pinCertSHA)
                    .add(appConfigs.openWeatherDomain, appConfigs.pinCertSHABackupA)
                    .add(appConfigs.openWeatherDomain, appConfigs.pinCertSHABackupB)
                    .build()
            )
            .build()
    }

    @Provides
    @Singleton
    @Named(OPEN_WEATHER_RETROFIT)
    fun provideRetrofit(
        @Named(OPEN_WEATHER_OKHTTP) okHttpClient: OkHttpClient,
        @Named(OPEN_WEATHER_BASE_URL) baseUrl: String,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(@Named(OPEN_WEATHER_RETROFIT) retrofit: Retrofit): IOpenWeatherApi =
        retrofit.create(IOpenWeatherApi::class.java)
}