package dev.givaldo.feature.main.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import dev.givaldo.feature.main.extention.navigate
import dev.givaldo.feature.main.fragment.MainFragmentDirections
import dev.givaldo.presentation.model.MovieBinding

class MainNavigationImpl(private val originFragment: Fragment) : MainNavigation {

    override fun navigateToMovieDetail(movie: MovieBinding, extras: FragmentNavigator.Extras) {
        originFragment.navigate(
            MainFragmentDirections.actionMainFragmentToMovieDetailFragment(movie), extras
        )
    }

    override fun navigateToMovieSearch() {
        originFragment.navigate(
            MainFragmentDirections.actionMainFragmentToMovieSearchFragment()
        )
    }

}