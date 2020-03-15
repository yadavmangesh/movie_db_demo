package com.mangesh.billeasydemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface DataSource {

    interface LocalDataSource {

        suspend fun getMoviesList():LiveData<MutableList<Movie>>

        suspend fun saveMovies(list: MutableList<Movie>)

    }

}