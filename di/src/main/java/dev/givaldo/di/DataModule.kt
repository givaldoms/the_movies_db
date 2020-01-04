package dev.givaldo.di

import dev.givaldo.data.datasource.local.MovieLocalDataSource
import dev.givaldo.data.datasource.remote.GenreRemoteDataSource
import dev.givaldo.data.datasource.remote.MovieRemoteDataSource
import dev.givaldo.data.repository.GenreRepositoryImpl
import dev.givaldo.data.repository.MovieRepositoryImpl
import dev.givaldo.data_local.core.AppDatabase
import dev.givaldo.data_local.datasource.MovieLocalDataSourceImpl
import dev.givaldo.data_remote.datasource.GenreRemoteDataSourceImpl
import dev.givaldo.data_remote.datasource.MovieRemoteDataSourceImpl
import dev.givaldo.data_remote.service.GenreWebService
import dev.givaldo.data_remote.service.MovieWebService
import dev.givaldo.data_remote.utils.WebServiceFactory
import dev.givaldo.domain.repository.GenreRepository
import dev.givaldo.domain.repository.MovieRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

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
        WebServiceFactory.createWebService<GenreWebService>()
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
        GenreRemoteDataSourceImpl(
            genreWebService = get()
        ) as GenreRemoteDataSource
    }

    single {
        MovieRepositoryImpl(
            local = get(),
            remote = get()
        ) as MovieRepository
    }

    single {
        GenreRepositoryImpl(
            local = get(),
            remote = get()
        ) as GenreRepository
    }

}