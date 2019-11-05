package dev.givaldo.feature.main.util

import androidx.navigation.fragment.FragmentNavigator
import dev.givaldo.presentation.model.MovieBinding
import java.io.Serializable

interface MoviesFragmentListener : Serializable {

    fun onItemClick(item: MovieBinding, extras: FragmentNavigator.Extras)

}