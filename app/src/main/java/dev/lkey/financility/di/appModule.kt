package dev.lkey.financility.di

import dev.lkey.financility.feature_articles.data.repository.ArticleRepositoryImpl
import dev.lkey.financility.feature_articles.domain.usecase.GetArticlesUseCase
import dev.lkey.financility.feature_articles.presentation.ArticlesViewModel
import dev.lkey.financility.feature_bill.data.repository.GetBillInfoRepositoryImpl
import dev.lkey.financility.feature_bill.domain.repository.GetBillInfoRepository
import dev.lkey.financility.feature_bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.financility.feature_bill.presentation.BillViewModel
import dev.lkey.financility.feature_transactions.data.db.AccountRepositoryImpl
import dev.lkey.financility.feature_transactions.data.repository.TransactionsRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository
import dev.lkey.financility.feature_transactions.domain.usecase.GetAccountUseCase
import dev.lkey.financility.feature_transactions.domain.usecase.GetTransactionsUseCase
import dev.lkey.financility.feature_transactions.domain.usecase.PostTransactionUseCase
import dev.lkey.financility.feature_transactions.presentation.expenses.create.CreateExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.expenses.history.HistoryExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.expenses.today.ExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.income.history.HistoryIncomeViewModel
import dev.lkey.financility.feature_transactions.presentation.income.today.IncomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    single { ArticleRepositoryImpl() }
    factory { GetArticlesUseCase(get()) }

    single { AccountRepositoryImpl(androidContext()) }
    single<TransactionsRepository> { TransactionsRepositoryImpl() }

    factory { GetTransactionsUseCase(get()) }
    factory { PostTransactionUseCase(get()) }
    factory { GetAccountUseCase(get(), get()) }

    single<GetBillInfoRepository> { GetBillInfoRepositoryImpl() }
    factory { GetBillInfoUseCase(get()) }

    viewModelOf(::ExpensesViewModel)
    viewModelOf(::IncomeViewModel)
    viewModelOf(::ArticlesViewModel)
    viewModelOf(::HistoryExpensesViewModel)
    viewModelOf(::HistoryIncomeViewModel)
    viewModelOf(::CreateExpensesViewModel)
    viewModelOf(::BillViewModel)

    viewModel { BillViewModel(get()) }


}
