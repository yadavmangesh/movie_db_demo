package com.mangesh.billeasydemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey
    var id:Double,

    @ColumnInfo(name = "poster")
    var poster:String?,

    @ColumnInfo(name = "release_data")
    var releaseDate:String,

    @ColumnInfo(name = "name")
    var name:String
)