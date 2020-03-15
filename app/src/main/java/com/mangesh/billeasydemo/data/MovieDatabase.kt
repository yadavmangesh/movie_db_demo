package com.mangesh.billeasydemo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Movie::class],version = 1,exportSchema = false)
abstract class MovieDatabase:RoomDatabase() {

    abstract fun movieDao():MoiveDao

    companion object{

        @Volatile
        private var INSTANCE:MovieDatabase?=null

        fun getDataBase(context:Context):MovieDatabase?{

            if (INSTANCE==null){
                synchronized(MovieDatabase::class.java){
                    if (INSTANCE==null){
                        INSTANCE=Room.databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java,
                            "movie_database").build()
                    }
                }
            }
           return INSTANCE
        }
    }
}