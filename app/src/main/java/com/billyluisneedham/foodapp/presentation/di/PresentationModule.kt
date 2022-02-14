package com.billyluisneedham.foodapp.presentation.di

import com.billyluisneedham.foodapp.presentation.util.expirationmessage.ExpirationMessage
import com.billyluisneedham.foodapp.presentation.util.expirationmessage.ExpirationMessageImpl
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
