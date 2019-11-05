package dev.givaldo.feature.main.util

import androidx.navigation.fragment.FragmentNavigator

typealias OnItemClickListener<T> = (T, FragmentNavigator.Extras) -> Unit
