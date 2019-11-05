package dev.givaldo.feature.main.fragment


import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dev.givaldo.feature.R
import dev.givaldo.feature.main.extention.setPicassoImage
import dev.givaldo.feature.main.extention.setToolbarIcon
import dev.givaldo.feature.main.extention.setToolbarTitle
import kotlinx.android.synthetic.main.app_bar_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarIcon()
        setupView()
    }

    private fun setupView() {
        movieDetailImageView.setPicassoImage(args.movie.posterUrl)
        movieDetailDescriptionTextView.text = args.movie.description
        setToolbarTitle(args.movie.title)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

}
