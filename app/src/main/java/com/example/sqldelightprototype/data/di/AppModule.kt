package com.example.sqldelightprototype.data.di

import com.example.sqldelightprototype.data.utils.time.TimeManager
import com.example.sqldelightprototype.data.utils.time.TimeManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

    @Binds
    abstract fun bindsTimeManager(timeManagerImpl: TimeManagerImpl): TimeManager
}