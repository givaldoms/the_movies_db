package dev.givaldo.cubos

import android.app.Application
import dev.givaldo.di.dataModule
import dev.givaldo.di.domainModule
import dev.givaldo.di.navigationModule
import dev.givaldo.di.presentationModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@InternalCoroutinesApi
@Suppress("unused")
class TheMovieDbApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {

        startKoin {
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    presentationModule,
                    navigationModule
                )
            ).androidContext(applicationContext)
        }

    }

}
