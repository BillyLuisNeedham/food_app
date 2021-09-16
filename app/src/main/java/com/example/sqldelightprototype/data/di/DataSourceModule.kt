package com.example.sqldelightprototype.data.di

import com.example.sqldelightprototype.data.localdatasource.FoodLocalDataSource
import com.example.sqldelightprototype.data.localdatasource.FoodLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindFoodLocalDataSource(foodLocalDataSourceImpl: FoodLocalDataSourceImpl): FoodLocalDataSource
}