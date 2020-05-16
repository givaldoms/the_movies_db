package dev.givaldo.feature.main.fragment


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.ui.setupActionBarWithNavController
import dev.givaldo.feature.R
import dev.givaldo.feature.main.extention.navigator
import dev.givaldo.feature.main.navigation.MainNavigation
import dev.givaldo.feature.main.pageadapter.MoviesPageAdapter
import dev.givaldo.feature.main.util.MoviesFragmentListener
import dev.givaldo.presentation.extensions.handle
import dev.givaldo.presentation.model.GenreBinding
import dev.givaldo.presentation.model.MovieBinding
import dev.givaldo.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), MoviesFragmentListener {

    private val navigation: MainNavigation by navigator()

    private val viewModel: MainViewModel by viewModel()
    private val moviesPageAdapter by lazy { MoviesPageAdapter(childFragmentManager, this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    private fun setupView() {
        mainViewPager.adapter = moviesPageAdapter
        mainTabLayout.setupWithViewPager(mainViewPager)
        setupToolbar()
        setHasOptionsMenu(true)
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(mainToolbar)
            setupActionBarWithNavController(requireView().findNavController())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.mainSearchMenu -> {
            navigation.navigateToMovieSearch()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.genresLiveData.observe(viewLifecycleOwner, Observer {
            handleGenresResult(it)
        })
    }

    override fun onItemClick(item: MovieBinding, extras: FragmentNavigator.Extras) = try {
        navigation.navigateToMovieDetail(item, extras)
    } catch (ignore: Exception) {
        // TODO handle error
    }

    private fun handleGenresResult(result: Result<List<GenreBinding>>) {
        result.handle {
            moviesPageAdapter.fragments = it
        }
    }
}
