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
import dev.lkey.financility.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.expenses.history.viewmodel.HistoryExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.expenses.today.viewmodel.ExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.income.history.viewmodel.HistoryIncomeViewModel
import dev.lkey.financility.feature_transactions.presentation.income.today.viewmodel.IncomeViewModel

/**
 * VM зависимости, инициализируемые Dagger-ом
 * */

//@Suppress("unused")
@Module
abstract class ViewModelModule {

    // common
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

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

    // feature transactions
    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    abstract fun bindExpensesViewModel(viewModel: ExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomeViewModel::class)
    abstract fun bindIncomeViewModel(viewModel: IncomeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HistoryExpensesViewModel::class)
    abstract fun bindHistoryExpensesViewModel(viewModel: HistoryExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryIncomeViewModel::class)
    abstract fun bindHistoryIncomeViewModel(viewModel: HistoryIncomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateExpensesViewModel::class)
    abstract fun bindCreateExpensesViewModel(viewModel: CreateExpensesViewModel): ViewModel

}
