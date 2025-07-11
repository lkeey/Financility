package dev.lkey.transations.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.core.di.utils.ViewModelKey
import dev.lkey.transations.presentation.create.viewmodel.CreateTransactionViewModel
import dev.lkey.transations.presentation.detail.viewmodel.UpdateTransactionViewModel
import dev.lkey.transations.presentation.expenses.history.viewmodel.HistoryExpensesViewModel
import dev.lkey.transations.presentation.expenses.today.viewmodel.ExpensesViewModel
import dev.lkey.transations.presentation.income.history.viewmodel.HistoryIncomeViewModel
import dev.lkey.transations.presentation.income.today.viewmodel.IncomeViewModel

/**
 * Модуль VM траназакций
 * */

@Module
interface TransactionsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    fun bindExpensesViewModel(viewModel: ExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomeViewModel::class)
    fun bindIncomeViewModel(viewModel: IncomeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HistoryExpensesViewModel::class)
    fun bindHistoryExpensesViewModel(viewModel: HistoryExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryIncomeViewModel::class)
    fun bindHistoryIncomeViewModel(viewModel: HistoryIncomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateTransactionViewModel::class)
    fun bindCreateExpensesViewModel(viewModel: CreateTransactionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateTransactionViewModel::class)
    fun bindUpdateTransactionViewModel(viewModel: UpdateTransactionViewModel): ViewModel

}
