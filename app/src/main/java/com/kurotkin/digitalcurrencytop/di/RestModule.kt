package com.kurotkin.digitalcurrencytop.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kurotkin.digitalcurrencytop.CoinGeckoApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RestModule {

    // создает конвертер для сериализации данных типа Gson
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    // создает клиент OkHttpClient, используемый библиотекой Retrofit для HTTP-запросов
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

    // создает Retrofit клиент, передает базовый адрес запроса, определяет Gson как конвертер данных, получаемых от сервера
    @Provides
    @Singleton
    @Named("COINGECKO_API")
    fun provideGeckoRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    // предоставляет подготовленный объект Api
    @Provides
    @Singleton
    fun provideGeckoApiService(@Named("COINGECKO_API") retrofit: Retrofit): CoinGeckoApi = retrofit.create(CoinGeckoApi::class.java)
}