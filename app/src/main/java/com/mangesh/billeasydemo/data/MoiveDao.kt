package com.mangesh.billeasydemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoiveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list:MutableList<Movie>)

    @Query("SELECT * FROM movie ORDER BY id ASC")
    fun getMovies():LiveData<MutableList<Movie>>


}