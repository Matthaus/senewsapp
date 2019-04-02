package br.com.matthaus.senewsapp.di

import br.com.matthaus.senewsapp.BuildConfig
import br.com.matthaus.senewsapp.network.NewsOrgAPI
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("X-Api-Key", BuildConfig.API_KEY)
                    .build()

                it.proceed(request)
            }
            .build()
    }

    single<NewsOrgAPI> { NewsOrgAPI.getInstance(get()) }

}