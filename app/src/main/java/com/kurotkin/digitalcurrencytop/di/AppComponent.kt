package com.kurotkin.digitalcurrencytop.di

import com.kurotkin.digitalcurrencytop.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MvpModule::class, ChartModule::class))
// содержит четыре модуля. Зависимости доступны для всех остальных модулей, объединенных в компоненте AppComponent
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)  // сообщает Даггеру класс, в который мы хотим внедрять зависимости
}