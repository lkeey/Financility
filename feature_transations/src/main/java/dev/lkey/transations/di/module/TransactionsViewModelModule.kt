package dev.lkey.transations.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.core.di.ViewModelKey
import dev.lkey.transations.presentation.create.viewmodel.CreateTransactionViewModel
import dev.lkey.transations.presentation.expenses.history.viewmodel.HistoryExpensesViewModel
import dev.lkey.transations.presentation.expenses.today.viewmodel.ExpensesViewModel
import dev.lkey.transations.presentation.income.history.viewmodel.HistoryIncomeViewModel
import dev.lkey.transations.presentation.income.today.viewmodel.IncomeViewModel
import dev.lkey.transations.presentation.update.UpdateTransactionViewModel

@Module
abstract class TransactionsViewModelModule {
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
    @ViewModelKey(CreateTransactionViewModel::class)
    abstract fun bindCreateExpensesViewModel(viewModel: CreateTransactionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateTransactionViewModel::class)
    abstract fun bindUpdateTransactionViewModel(viewModel: UpdateTransactionViewModel): ViewModel

}
