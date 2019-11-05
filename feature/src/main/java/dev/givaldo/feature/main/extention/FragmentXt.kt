@file:Suppress("UNCHECKED_CAST", "unused")

package dev.givaldo.feature.main.extention

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dev.givaldo.feature.R
import kotlinx.android.synthetic.main.app_bar_movie_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.io.Serializable

inline fun <F : Fragment, reified V : Any> F.navigator() = inject<V> {
    parametersOf(this)
}

fun <T : Parcelable> Fragment.getParcelableBundle(key: String) = arguments?.getParcelable<T>(key)

fun <T : Serializable> Fragment.getSerializableBundle(key: String): T? =
    arguments?.getSerializable(key) as T?

fun Fragment.getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(requireContext(), id)

fun Fragment.setToolbarTitle(title: String) {
    (context as? AppCompatActivity)?.supportActionBar?.title = title
}

fun Fragment.setToolbarIcon(@DrawableRes icon: Int? = R.drawable.ic_arrow_back_black_24dp) {
    (requireActivity() as AppCompatActivity).apply {
        setSupportActionBar(movieDetailToolbar)
        setupActionBarWithNavController(requireView().findNavController())
        if (icon != null) {
            movieDetailToolbar.navigationIcon = getDrawable(icon)
        }
    }
}