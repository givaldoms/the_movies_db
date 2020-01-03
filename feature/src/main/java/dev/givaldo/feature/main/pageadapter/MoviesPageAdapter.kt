package dev.givaldo.feature.main.pageadapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import dev.givaldo.feature.main.fragment.MoviesFragment
import dev.givaldo.feature.main.util.MoviesFragmentListener
import dev.givaldo.presentation.model.GenreBinding

class MoviesPageAdapter(fragmentManager: FragmentManager, val listener: MoviesFragmentListener) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragments = listOf<GenreBinding>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int) = MoviesFragment.newInstance(fragments[position], listener)

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int) = fragments[position].title
}