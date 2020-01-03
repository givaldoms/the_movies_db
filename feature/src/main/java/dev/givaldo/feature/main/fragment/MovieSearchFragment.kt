package dev.givaldo.feature.main.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dev.givaldo.feature.R
import dev.givaldo.feature.main.adapter.MoviesAdapter
import dev.givaldo.feature.main.extention.navigator
import dev.givaldo.feature.main.extention.onQueryTextChange
import dev.givaldo.feature.main.extention.onScrollFinish
import dev.givaldo.feature.main.navigation.MovieSearchNavigation
import dev.givaldo.presentation.extensions.handle
import dev.givaldo.presentation.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_movie_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieSearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()
    private val navigation: MovieSearchNavigation by navigator()

    private val moviesAdapter by lazy { MoviesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupToolbar()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        moviesAdapter.onItemClickListener = { movie, extras ->
            navigation.navigateToMovieDetail(movie, extras)
        }

        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            it.handle { list ->
                moviesAdapter.items = list
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.movie_search_menu, menu)
        val searchItem = menu.findItem(R.id.movieSearchMenu)
        setupSearchView(searchItem)
    }

    private fun setupSearchView(searchItem: MenuItem) {
        searchItem.expandActionView()

        (searchItem.actionView as SearchView).run {
            queryHint = getString(R.string.search_movies_hint)
            onQueryTextChange { viewModel.fetchMovies(it) }
        }
    }

    private fun setupView() {

        movieSearchRecyclerView.apply {
            adapter = moviesAdapter
            onScrollFinish {
                viewModel.fetchMoreMovie()
            }
        }

        setHasOptionsMenu(true)
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(movieSearchToolbar)
            setupActionBarWithNavController(requireView().findNavController())
            movieSearchToolbar.navigationIcon = getDrawable(R.drawable.ic_arrow_back_black_24dp)
        }
    }
}