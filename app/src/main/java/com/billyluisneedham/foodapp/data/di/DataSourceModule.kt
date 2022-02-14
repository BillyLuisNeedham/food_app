package com.billyluisneedham.foodapp.data.di

import com.billyluisneedham.foodapp.data.localdatasource.FoodLocalDataSource
import com.billyluisneedham.foodapp.data.localdatasource.FoodLocalDataSourceImpl
import com.billyluisneedham.foodapp.data.localdatasource.UserLocalDataSource
import com.billyluisneedham.foodapp.data.localdatasource.UserLocalDataSourceImpl
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
