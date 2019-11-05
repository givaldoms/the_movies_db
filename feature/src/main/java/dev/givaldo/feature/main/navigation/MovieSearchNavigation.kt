package dev.givaldo.feature.main.navigation

import androidx.navigation.fragment.FragmentNavigator
import dev.givaldo.presentation.model.MovieBinding

interface MovieSearchNavigation {

    fun navigateToMovieDetail(movie: MovieBinding, extras: FragmentNavigator.Extras)

}