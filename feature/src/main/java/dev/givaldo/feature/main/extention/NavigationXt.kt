@file:Suppress("unused")

package dev.givaldo.feature.main.extention

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.findNavController

fun View.navigate(directions: NavDirections, navOptions: NavOptions? = null) = try {
    findNavController().navigate(directions, navOptions)
} catch (e: Exception) {
    e.printStackTrace()
}

fun View.navigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) = try {
    findNavController().navigate(resId, args, navOptions)
} catch (e: Exception) {
    e.printStackTrace()
}

fun View.navigate(directions: NavDirections, navigatorExtras: Navigator.Extras) = try {
    findNavController().navigate(directions, navigatorExtras)
} catch (e: Exception) {
    e.printStackTrace()
}

fun Fragment.navigate(directions: NavDirections, navOptions: NavOptions? = null) =
    requireView().navigate(directions, navOptions)


fun Fragment.navigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) =
    requireView().navigate(resId, args, navOptions)

fun Fragment.navigate(directions: NavDirections, navigatorExtras: Navigator.Extras) =
    requireView().navigate(directions, navigatorExtras)
