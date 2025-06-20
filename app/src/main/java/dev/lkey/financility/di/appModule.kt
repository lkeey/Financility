package dev.lkey.financility.di

import dev.lkey.financility.feature_articles.presentation.ArticlesViewModel
import dev.lkey.financility.feature_transactions.data.db.AccountRepositoryImpl
import dev.lkey.financility.feature_transactions.data.repository.TransactionsRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository
import dev.lkey.financility.feature_transactions.domain.usecase.GetAccountUseCase
import dev.lkey.financility.feature_transactions.presentation.expenses.create.CreateExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.expenses.history.HistoryExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.expenses.today.ExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.income.history.HistoryIncomeViewModel
import dev.lkey.financility.feature_transactions.presentation.income.today.IncomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::ExpensesViewModel)
    viewModelOf(::IncomeViewModel)
    viewModelOf(::ArticlesViewModel)
    viewModelOf(::HistoryExpensesViewModel)
    viewModelOf(::HistoryIncomeViewModel)
    viewModelOf(::CreateExpensesViewModel)

    single { AccountRepositoryImpl(androidContext()) }

    single<TransactionsRepository> { TransactionsRepositoryImpl() }

    factory { GetAccountUseCase(get(), get()) }

}
