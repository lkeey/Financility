package dev.lkey.financility.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.core.di.ViewModelFactory
import dev.lkey.core.di.ViewModelKey
import dev.lkey.transations.presentation.expenses.create.viewmodel.CreateExpensesViewModel
import dev.lkey.transations.presentation.expenses.history.viewmodel.HistoryExpensesViewModel
import dev.lkey.transations.presentation.expenses.today.viewmodel.ExpensesViewModel
import dev.lkey.transations.presentation.income.history.viewmodel.HistoryIncomeViewModel
import dev.lkey.transations.presentation.income.today.viewmodel.IncomeViewModel

/**
 * VM зависимости, инициализируемые Dagger-ом
 * */

//@Suppress("unused")
@Module
abstract class ViewModelModule {

    // common
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // feature bill

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
