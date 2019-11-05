package dev.givaldo.di

import androidx.fragment.app.Fragment
import dev.givaldo.feature.main.navigation.MainNavigation
import dev.givaldo.feature.main.navigation.MainNavigationImpl
import dev.givaldo.feature.main.navigation.MovieSearchNavigation
import dev.givaldo.feature.main.navigation.MovieSearchNavigationImpl
import org.koin.dsl.module

val navigationModule = module {

    factory { (originFragment: Fragment) ->
        MainNavigationImpl(originFragment) as MainNavigation
    }


    factory { (originFragment: Fragment) ->
        MovieSearchNavigationImpl(originFragment) as MovieSearchNavigation
    }


}