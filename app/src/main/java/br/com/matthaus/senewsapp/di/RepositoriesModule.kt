package br.com.matthaus.senewsapp.di

import br.com.matthaus.senewsapp.repositories.SourceRepository
import br.com.matthaus.senewsapp.repositories.TopHeadlineArticleRepository
import org.koin.dsl.module

val repositoriesModule = module {

    single<SourceRepository> { SourceRepository(get()) }

    single<TopHeadlineArticleRepository> { TopHeadlineArticleRepository(get()) }

}