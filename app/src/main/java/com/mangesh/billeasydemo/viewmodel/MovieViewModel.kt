package com.mangesh.billeasydemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mangesh.billeasydemo.data.*

class MovieViewModel(application: Application):AndroidViewModel(application) {


      private var moviesRepositoryImpl:MoviesRepositoryImpl=MoviesRepositoryImpl(getLocalDataSource())

      private var movieRemoteDataSource = getRemoteDataSource()

      lateinit var movieList:LiveData<MutableList<Movie>>

    init {
        movieRemoteDataSource.getData()
    }


    private fun getLocalDataSource():MovieLocalDataSource{
        val movieDatabase=MovieDatabase.getDataBase(getApplication())
        return MovieLocalDataSource(movieDatabase!!.movieDao())

    }

     private fun getRemoteDataSource():MovieRemoteDataSource{
     return MovieRemoteDataSource(moviesRepositoryImpl,getApplication())

    }

    suspend fun getData():LiveData<MutableList<Movie>>{
         return moviesRepositoryImpl.getData()
    }
}