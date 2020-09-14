package com.example.deggerhiltplayground.di

import com.example.deggerhiltplayground.Retrofit.BlogRetrofit
import com.example.deggerhiltplayground.Retrofit.NetworkMappper
import com.example.deggerhiltplayground.repository.MainRepository
import com.example.deggerhiltplayground.room.BlogDao
import com.example.deggerhiltplayground.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blodDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMappper: NetworkMappper
    ):MainRepository{
        return MainRepository(blodDao,blogRetrofit,cacheMapper,networkMappper)
    }
}