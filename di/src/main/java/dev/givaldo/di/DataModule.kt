package dev.givaldo.di

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.data.repository.MovieRepositoryImpl
import dev.givaldo.data_local.core.AppDatabase
import dev.givaldo.data_local.datasource.MovieLocalDataSourceImpl
import dev.givaldo.data_remote.datasource.MovieRemoteDataSourceImpl
import dev.givaldo.data_remote.service.MovieWebService
import dev.givaldo.data_remote.utils.WebServiceFactory
import dev.givaldo.domain.repository.MovieRepository
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

@FlowPreview
val dataModule = module {

    single {
        AppDatabase.getInstance(androidApplication())
    }

    single {
        get<AppDatabase>().movieDao()
    }

    single {
        get<AppDatabase>().genreDao()
    }

    single {
        WebServiceFactory.createWebService<MovieWebService>()
    }

    single {
        MovieLocalDataSourceImpl(
            movieDao = get(),
            genreDao = get()
        ) as MovieLocalDataSource
    }

    single {
        MovieRemoteDataSourceImpl(
            movieWebService = get()
        ) as MovieRemoteDataSource
    }

    single {
        MovieRepositoryImpl(
            local = get(),
            remote = get()
        ) as MovieRepository
    }

}