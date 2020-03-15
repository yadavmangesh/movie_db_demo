package com.mangesh.billeasydemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val localDataSource: DataSource.LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher=Dispatchers.IO
):MovieRepository  {

    override suspend fun getData(): LiveData<MutableList<Movie>> {
        return localDataSource.getMoviesList()
    }


    override suspend fun saveData(list: MutableList<Movie>) {

         withContext(ioDispatcher){
             localDataSource.saveMovies(list)

         }
    }

}