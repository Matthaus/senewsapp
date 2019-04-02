package br.com.matthaus.senewsapp.di

import br.com.matthaus.senewsapp.viewmodel.ArticlesViewModel
import br.com.matthaus.senewsapp.viewmodel.SourcesViewModel
import org.koin.dsl.module

var viewModelFactoriesModule = module {

    factory<SourcesViewModel.SourcesViewModelFactory> { SourcesViewModel.SourcesViewModelFactory(get()) }

    factory<ArticlesViewModel.NewsViewModelFactory> { ArticlesViewModel.NewsViewModelFactory(get()) }

}