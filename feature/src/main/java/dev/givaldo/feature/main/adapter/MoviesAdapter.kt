package dev.givaldo.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.givaldo.feature.R
import dev.givaldo.feature.main.util.OnItemClickListener
import dev.givaldo.presentation.model.MovieBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    var onItemClickListener: OnItemClickListener<MovieBinding>? = null

    var items = listOf<MovieBinding>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.setupItem(items[position], onItemClickListener)
    }


}

