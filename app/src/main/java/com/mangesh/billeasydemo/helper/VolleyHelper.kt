package com.mangesh.billeasydemo.helper

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class VolleyHelper constructor(context: Context) {

    val ioDispatcher:CoroutineDispatcher=Dispatchers.IO

    companion object {
        @Volatile
        private var INSTANCE: VolleyHelper? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: VolleyHelper(context).also {
                    INSTANCE = it
                }
            }
    }
    val imageLoader: ImageLoader by lazy {
        ImageLoader(requestQueue,
                object : ImageLoader.ImageCache {
                    private val cache = LruCache<String, Bitmap>(20)
                    override fun getBitmap(url: String): Bitmap {
                        return cache.get(url)
                    }
                    override fun putBitmap(url: String, bitmap: Bitmap) {
                        cache.put(url, bitmap)
                    }
                })
    }
    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }


     fun getRequest(url: String,apiReponseListener: ApiReponseListener): JsonObjectRequest {

        val jsonObjectRequest= object :JsonObjectRequest(Request.Method.GET, url, null,

            Response.Listener { response ->
                Log.d("VolleyHelper","getRequest $response")
                apiReponseListener.onSuccess(response.toString())
            },
            Response.ErrorListener { error ->
                Log.d("VolleyHelper","error ${error.message}")
                apiReponseListener.onError(error)

            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }

        return jsonObjectRequest

    }



}