package com.billyluisneedham.foodapp.data.di

import com.billyluisneedham.foodapp.data.utils.time.TimeManager
import com.billyluisneedham.foodapp.data.utils.time.TimeManagerImpl
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
