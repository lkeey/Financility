package dev.lkey.articles.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.articles.presentation.viewmodel.ArticlesViewModel
import dev.lkey.core.di.ViewModelKey

@Module
abstract class ArticlesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindArticlesViewModel(viewModel: ArticlesViewModel): ViewModel

}
