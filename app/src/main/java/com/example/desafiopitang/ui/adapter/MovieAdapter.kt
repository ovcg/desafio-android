package com.example.desafiopitang.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopitang.R
import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.util.ImgUtil
import com.example.desafiopitang.util.interfaces.ClickMovieListener
import com.example.desafiopitang.util.interfaces.RecyclerViewImpl
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
    var ctx : Context,
    var movies : ArrayList<Movie>,
    var movieListener: ClickMovieListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(),
    RecyclerViewImpl<Movie>{

    override fun clearList() {
        this.movies.clear()
        notifyItemRangeRemoved(0, this.movies.size)
    }

    fun setList(list: ArrayList<Movie>){
        addAll(list)
    }

    override fun addAll(list: ArrayList<Movie>) {
        clearList()
        this.movies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(movies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardMovie : CardView = itemView.cv_movie
        private val name : TextView = itemView.tv_movie_name
        private val iv : ImageView = itemView.iv_movie

        fun onBind(movie : Movie){
                name.text = movie.name
                ImgUtil.loadImg(ctx, movie.url, iv)
                cardMovie.setOnClickListener { movieListener.movieSelected(movie) }
        }
    }
}