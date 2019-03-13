package com.kurotkin.digitalcurrencytop.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Предоставлять контекст для доступа к ресурсам в любой части приложения
@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context = app
}