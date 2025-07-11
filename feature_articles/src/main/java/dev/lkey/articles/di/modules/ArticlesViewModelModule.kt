package dev.lkey.articles.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.articles.presentation.viewmodel.ArticlesViewModel
import dev.lkey.core.di.utils.ViewModelKey

/**
 * Модуль VM статей расходов, инициализируемая Dagger-ом
 * */


@Module
interface ArticlesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    fun bindArticlesViewModel(viewModel: ArticlesViewModel): ViewModel

}
