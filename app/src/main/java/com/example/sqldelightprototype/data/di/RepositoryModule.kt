package com.example.sqldelightprototype.data.di

import com.example.sqldelightprototype.data.repositories.FoodRepositoryImpl
import com.example.sqldelightprototype.domain.repositories.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): FoodRepository
}