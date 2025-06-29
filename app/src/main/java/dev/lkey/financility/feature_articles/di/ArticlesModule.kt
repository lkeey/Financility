package dev.lkey.financility.feature_articles.di

import dev.lkey.financility.feature_articles.data.repository.ArticleRepositoryImpl
import dev.lkey.financility.feature_articles.domain.usecase.GetArticlesUseCase
import dev.lkey.financility.feature_articles.presentation.viewmodel.ArticlesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val articleModule = module {

    // Репозиторий
    single { ArticleRepositoryImpl() }

    // Use-case
    factory { GetArticlesUseCase(get()) }

    // ViewModel
    viewModel { ArticlesViewModel(get()) }

}