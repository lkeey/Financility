package dev.lkey.articles.di

import dagger.Module
import dev.lkey.articles.di.modules.ArticlesRepositoryModule
import dev.lkey.articles.di.modules.ArticlesUseCaseModule
import dev.lkey.articles.di.modules.ArticlesViewModelModule


@Module(
    includes = [
        ArticlesRepositoryModule::class,
        ArticlesUseCaseModule::class,
        ArticlesViewModelModule::class
    ]
)
class ArticleModule
