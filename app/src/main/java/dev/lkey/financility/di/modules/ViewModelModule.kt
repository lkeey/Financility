package dev.lkey.financility.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.financility.di.ViewModelFactory
import dev.lkey.financility.di.ViewModelKey
import dev.lkey.financility.feature_articles.presentation.viewmodel.ArticlesViewModel

/**
 * VM зависимости, инициализируемые Dagger-ом
 * */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindArticlesViewModel(viewModel: ArticlesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
