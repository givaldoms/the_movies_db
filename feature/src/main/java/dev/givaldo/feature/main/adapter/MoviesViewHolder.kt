package dev.givaldo.feature.main.adapter

import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import dev.givaldo.feature.R
import dev.givaldo.feature.main.extention.setPicassoImage
import dev.givaldo.feature.main.util.OnItemClickListener
import dev.givaldo.presentation.model.MovieBinding
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setupItem(movie: MovieBinding, listener: OnItemClickListener<MovieBinding>?) {
        view.apply {
            movieTitleTextView.text = movie.title
            moviePosterImageView.setPicassoImage(movie.posterUrl)

            val extra = FragmentNavigatorExtras(
                moviePosterImageView to context.getString(R.string.poster_transition_name)
            )

            setOnClickListener {
                listener?.invoke(movie, extra)
            }

        }
    }

}

