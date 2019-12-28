package dev.givaldo.feature.main.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.givaldo.feature.R
import dev.givaldo.feature.main.adapter.MoviesAdapter
import dev.givaldo.feature.main.extention.getParcelableBundle
import dev.givaldo.feature.main.extention.getSerializableBundle
import dev.givaldo.feature.main.extention.onScrollFinish
import dev.givaldo.feature.main.util.MoviesFragmentListener
import dev.givaldo.presentation.extensions.handle
import dev.givaldo.presentation.model.GenreBinding
import dev.givaldo.presentation.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@ExperimentalCoroutinesApi
@FlowPreview
class MoviesFragment : Fragment() {

    private val moviesAdapter by lazy { MoviesAdapter() }
    private val genreBundle by lazy { getParcelableBundle<GenreBinding>(GENRE_BUNDLE) }

    private val listener by lazy {
        getSerializableBundle<MoviesFragmentListener>(LISTENER_BUNDLE)
    }

    private val viewModel: MoviesViewModel by viewModel { parametersOf(genreBundle) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        moviesRecyclerView.apply {
            adapter = moviesAdapter
            onScrollFinish {
                viewModel.fetchMovies()
            }
        }

        moviesAdapter.onItemClickListener = { item, extra ->
            listener?.onItemClick(item, extra)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            it.handle { movie ->
                moviesAdapter.items = movie
            }
        })
    }

    companion object {

        private const val GENRE_BUNDLE = "genre_bundle"
        private const val LISTENER_BUNDLE = "listener_bundle"

        fun newInstance(
            genre: GenreBinding,
            listener: MoviesFragmentListener
        ) = MoviesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(GENRE_BUNDLE, genre)
                putSerializable(LISTENER_BUNDLE, listener)
            }
        }
    }

}
