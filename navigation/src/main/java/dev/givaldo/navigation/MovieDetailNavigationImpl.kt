package dev.givaldo.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dev.givaldo.feature.main.fragment.MovieDetailFragmentArgs
import dev.givaldo.feature.main.navigation.MovieDetailNavigation
import dev.givaldo.presentation.model.MovieBinding

class MovieDetailNavigationImpl(originFragment: Fragment) : MovieDetailNavigation {

    private val args = originFragment.navArgs<MovieDetailFragmentArgs>().value

    override val movieArg: MovieBinding = args.movie
}