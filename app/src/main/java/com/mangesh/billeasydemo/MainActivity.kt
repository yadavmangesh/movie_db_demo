package com.mangesh.billeasydemo

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mangesh.billeasydemo.data.Movie
import com.mangesh.billeasydemo.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private  var movieViewModel: MovieViewModel= MovieViewModel(MovieApplication.applicationContext() as Application)

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        movieAdapter= MovieAdapter(this);

        rv_movie.apply {
            adapter=movieAdapter
        }

           launch {
               movieViewModel.getData().observe(this@MainActivity, Observer {
                   movieAdapter.updateList(it)
               })
           }


    }

}
