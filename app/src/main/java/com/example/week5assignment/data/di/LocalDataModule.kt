package com.example.week5assignment.data.di

import android.content.Context
import com.example.week5assignment.data.local.database.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//bagimlilik azaltmak icin di kullanildi
@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun providePostsDatabase(@ApplicationContext appContext : Context): PostsDatabase {
        return PostsDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun providePostDao(db : PostsDatabase) = db.postDao()
}