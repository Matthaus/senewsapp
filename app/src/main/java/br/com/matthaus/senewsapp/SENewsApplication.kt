package br.com.matthaus.senewsapp

import android.app.Application
import br.com.matthaus.senewsapp.di.networkModule
import br.com.matthaus.senewsapp.di.repositoriesModule
import br.com.matthaus.senewsapp.di.viewModelFactoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SENewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SENewsApplication)
            modules(networkModule, repositoriesModule, viewModelFactoriesModule)
        }
    }
}