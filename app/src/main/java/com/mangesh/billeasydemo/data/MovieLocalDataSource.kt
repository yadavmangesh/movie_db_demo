package com.mangesh.billeasydemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieLocalDataSource(private val movieDao:MoiveDao,
                           private val ioDispatcher:CoroutineDispatcher=Dispatchers.IO) :DataSource.LocalDataSource {


    override suspend fun saveMovies(list: MutableList<Movie>) {
        withContext(ioDispatcher){
            movieDao.insert(list)
        }
    }

    override suspend fun getMoviesList(): LiveData<MutableList<Movie>> = withContext(ioDispatcher){
           return@withContext movieDao.getMovies()
    }

}