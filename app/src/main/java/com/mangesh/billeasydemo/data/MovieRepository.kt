package com.mangesh.billeasydemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface MovieRepository {

    suspend fun getData():LiveData<MutableList<Movie>>

    suspend fun saveData(list: MutableList<Movie>)
}