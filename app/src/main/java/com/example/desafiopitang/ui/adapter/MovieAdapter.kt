package com.example.desafiopitang.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.util.interfaces.ClickMovie
import com.example.desafiopitang.util.interfaces.RecyclerViewImpl

abstract class MovieAdapter(
    var ctx : Context,
    var movies : MutableList<Movie>,
    movieListener: ClickMovie
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(),
    RecyclerViewImpl<Movie>{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}