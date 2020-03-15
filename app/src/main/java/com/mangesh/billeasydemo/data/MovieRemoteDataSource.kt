package com.mangesh.billeasydemo.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.mangesh.billeasydemo.helper.ApiReponseListener
import com.mangesh.billeasydemo.helper.VolleyHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class MovieRemoteDataSource(private val movieRepository: MovieRepository,
                            private val application: Application,
                            private val ioDispatcher:
CoroutineDispatcher=Dispatchers.IO) {


    private var volleyHelper:VolleyHelper=VolleyHelper.getInstance(application)

    val queue: RequestQueue =volleyHelper.requestQueue

     suspend fun saveMovies(list: MutableList<Movie>) {
        withContext(ioDispatcher){
            movieRepository.saveData(list)
        }
    }

     fun getData() {

        val url="https://api.themoviedb.org/3/movie/now_playing?api_key=b4f3c29056b43913db71b750c11e5f1f&language=en-US&page=1"

        val request=volleyHelper.getRequest(url,object : ApiReponseListener {

           override   fun onSuccess(response: String) {
              Log.d("MovieRemoteDataSource",response)
                runBlocking {
                    saveMovies(parseData(response))
                }
            }

            override  fun onError(volleyError: VolleyError) {
                Log.d("MovieRemoteDataSource ","onError  ${volleyError.networkResponse}")
            }

        })
        queue.add(request)
    }

    private fun parseData(response:String):MutableList<Movie>{

        val mainObject=JSONObject(response)

        val list:MutableList<Movie> = mutableListOf()

        val url="http://image.tmdb.org/t/p/w185"

        val resultArray:JSONArray=mainObject.getJSONArray("results")

        for (i in 0 until resultArray.length()){
            val movieObject=resultArray[i] as JSONObject

            val moviePoster:String =url+movieObject.getString("poster_path")

            val name=movieObject.getString("title")

            val releaseDate=movieObject.getString("release_date")

            val id=movieObject.getDouble("id")

            val movie=Movie(id,moviePoster,releaseDate,name)

           list.add(movie)
        }
        Log.d("VolleyHelper","getRequest ${list.size}")
        return list
    }
}