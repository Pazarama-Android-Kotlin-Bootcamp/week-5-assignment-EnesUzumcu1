package com.example.week5assignment.data.di

import com.example.week5assignment.data.remote.api.ApiServiceUser
import com.example.week5assignment.data.repository.UserRepository
import com.example.week5assignment.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

//bagimlilik azaltmak icin di kullanildi
@Module
@InstallIn(ViewModelComponent::class)
class UsersModule {

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiServiceUser {
        return retrofit.create(ApiServiceUser::class.java)
    }

    @Provides
    fun provideUserRepository(apiServiceUser: ApiServiceUser) : UserRepository{
        return UserRepositoryImpl(apiServiceUser)
    }
}