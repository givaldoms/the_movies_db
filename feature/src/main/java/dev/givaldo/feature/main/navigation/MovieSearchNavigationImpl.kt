package dev.givaldo.feature.main.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import dev.givaldo.feature.main.extention.navigate
import dev.givaldo.feature.main.fragment.MovieSearchFragmentDirections
import dev.givaldo.presentation.model.MovieBinding

class MovieSearchNavigationImpl(private val originFragment: Fragment) : MovieSearchNavigation {

    override fun navigateToMovieDetail(movie: MovieBinding, extras: FragmentNavigator.Extras) {
        originFragment.navigate(
            MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment(movie),
            extras
        )
    }
}