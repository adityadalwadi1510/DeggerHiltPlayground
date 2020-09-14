package com.example.deggerhiltplayground.repository

import com.example.deggerhiltplayground.Model.Blog
import com.example.deggerhiltplayground.Retrofit.BlogRetrofit
import com.example.deggerhiltplayground.Retrofit.NetworkMappper
import com.example.deggerhiltplayground.Util.DataState
import com.example.deggerhiltplayground.room.BlogDao
import com.example.deggerhiltplayground.room.CacheMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMappper: NetworkMappper
){
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {

            val networkBlog=blogRetrofit.get()
            val blogs=networkMappper.mapFromEntityList(networkBlog)
            for (bolg in blogs){
                blogDao.insert(cacheMapper.mapToEntity(bolg))
            }
            val cacheBlog=blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheBlog)))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }
}
