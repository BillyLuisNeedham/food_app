package com.example.sqldelightprototype.data.di

import com.example.sqldelightprototype.data.localdatasource.FoodLocalDataSource
import com.example.sqldelightprototype.data.localdatasource.FoodLocalDataSourceImpl
import com.example.sqldelightprototype.data.localdatasource.UserLocalDataSource
import com.example.sqldelightprototype.data.localdatasource.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindFoodLocalDataSource(foodLocalDataSourceImpl: FoodLocalDataSourceImpl): FoodLocalDataSource

    @Binds
    abstract fun bindUserLocalDataSource(userLocalDataSourceImpl: UserLocalDataSourceImpl): UserLocalDataSource
}