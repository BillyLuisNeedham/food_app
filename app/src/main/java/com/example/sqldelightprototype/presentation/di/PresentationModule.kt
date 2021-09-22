package com.example.sqldelightprototype.presentation.di

import com.example.sqldelightprototype.presentation.util.expirationmessage.ExpirationMessage
import com.example.sqldelightprototype.presentation.util.expirationmessage.ExpirationMessageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsExpirationMessage(expirationMessageImpl: ExpirationMessageImpl): ExpirationMessage
}