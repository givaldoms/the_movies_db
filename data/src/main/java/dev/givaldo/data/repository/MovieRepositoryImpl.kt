package dev.givaldo.data.repository

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.combine

@ExperimentalCoroutinesApi
@FlowPreview
class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource
) : MovieRepository {

    override fun getMoviesByGenre(genreId: Long, page: Int) = remote.getMoviesByGenre(genreId, page)
    /*local.getMoviesByGenre(genreId)
        .combine(remote.getMoviesByGenre(genreId, page).withDefaultValue()) { l, r ->
            local.saveMovies(r)
            l
        }*/


    override fun getMoviesQuery(query: String, page: Int) = remote.getMoviesByQuery(query, page)

//            = flow {
//        local.getMoviesByQuery(query).distinctUntilChanged()
//            .onEach {
//                emit(it)
//            }
//            .flatMapConcat {
//                remote.getMoviesByQuery(query, page).flatMapConcat {
//                    local.saveMovies(it)
//                }
//            }
//            .collect()
//    }
}