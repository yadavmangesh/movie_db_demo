package com.mangesh.billeasydemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mangesh.billeasydemo.data.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val context: Context): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private var movieList:MutableList<Movie> = mutableListOf()

    class MovieHolder(itemView: View):RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
       return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
       val movie=movieList[position]

        holder.itemView.tv_date.text ="Release Date:"+movie.releaseDate
        holder.itemView.tv_title.text=movie.name

        Glide.with(context).load(movie.poster).into(holder.itemView.iv_movie_poster)
    }

    fun updateList(list: MutableList<Movie>){
        movieList=list
        notifyDataSetChanged()
    }


}