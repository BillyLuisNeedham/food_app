package com.billyluisneedham.foodapp.data.di

import com.billyluisneedham.foodapp.data.repositories.FoodRepositoryImpl
import com.billyluisneedham.foodapp.data.repositories.UserRepositoryImpl
import com.billyluisneedham.foodapp.domain.repositories.FoodRepository
import com.billyluisneedham.foodapp.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): com.billyluisneedham.foodapp.domain.repositories.FoodRepository

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): com.billyluisneedham.foodapp.domain.repositories.UserRepository
}
