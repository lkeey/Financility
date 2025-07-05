package dev.lkey.financility.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.financility.di.ViewModelFactory
import dev.lkey.financility.di.ViewModelKey
import dev.lkey.financility.feature_articles.presentation.viewmodel.ArticlesViewModel
import dev.lkey.financility.feature_bill.presentation.current.viewmodel.BillViewModel
import dev.lkey.financility.feature_bill.presentation.edit.viewmodel.EditBillViewModel

/**
 * VM зависимости, инициализируемые Dagger-ом
 * */

//@Suppress("unused")
@Module
abstract class ViewModelModule {

    // feature articles
    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindArticlesViewModel(viewModel: ArticlesViewModel): ViewModel

    // feature bill
    @Binds
    @IntoMap
    @ViewModelKey(BillViewModel::class)
    abstract fun bindBillViewModel(viewModel: BillViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditBillViewModel::class)
    abstract fun bindEditBillViewModel(viewModel: EditBillViewModel): ViewModel

    // common
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
