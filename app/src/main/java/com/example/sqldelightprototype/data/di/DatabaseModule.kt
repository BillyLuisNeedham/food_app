package com.example.sqldelightprototype.data.di

import android.content.Context
import com.example.sqldelightprototype.data.localdatasource.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideFoodDatabase(@ApplicationContext appContext: Context): FoodDatabase {
        return FoodDatabase(context = appContext)
    }

}