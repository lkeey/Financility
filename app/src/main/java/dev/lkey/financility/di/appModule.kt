package dev.lkey.financility.di

import dev.lkey.financility.feature_articles.data.repository.ArticleRepositoryImpl
import dev.lkey.financility.feature_articles.domain.usecase.GetArticlesUseCase
import dev.lkey.financility.feature_articles.presentation.viewmodel.ArticlesViewModel
import dev.lkey.financility.feature_bill.data.repository.BillRepositoryImpl
import dev.lkey.financility.feature_bill.domain.repository.BillRepository
import dev.lkey.financility.feature_bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.financility.feature_bill.domain.usecase.UpdateBillUseCase
import dev.lkey.financility.feature_bill.presentation.viewmodel.BillViewModel
import dev.lkey.financility.feature_transactions.data.db.AccountRepositoryImpl
import dev.lkey.financility.feature_transactions.data.repository.TransactionsRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository
import dev.lkey.financility.feature_transactions.domain.usecase.GetAccountUseCase
import dev.lkey.financility.feature_transactions.domain.usecase.GetTransactionsUseCase
import dev.lkey.financility.feature_transactions.domain.usecase.PostTransactionUseCase
import dev.lkey.financility.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.expenses.history.viewmodel.HistoryExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.expenses.today.viewmodel.ExpensesViewModel
import dev.lkey.financility.feature_transactions.presentation.income.history.viewmodel.HistoryIncomeViewModel
import dev.lkey.financility.feature_transactions.presentation.income.today.viewmodel.IncomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Зависимости, инициализируемые Koin-ом
 * */

val appModule = module {


    single { ArticleRepositoryImpl() }
    factory { GetArticlesUseCase(get()) }

    single { AccountRepositoryImpl(androidContext()) }
    single<TransactionsRepository> { TransactionsRepositoryImpl() }

    factory { GetTransactionsUseCase(get()) }
    factory { PostTransactionUseCase(get()) }

    factory { GetAccountUseCase(get(), get()) }
    factory { UpdateBillUseCase(get()) }

    single<BillRepository> { BillRepositoryImpl() }
    factory { GetBillInfoUseCase(get()) }

    viewModel { BillViewModel(get(), get()) }
    viewModel { ExpensesViewModel(get(), get()) }
    viewModel { IncomeViewModel(get(), get()) }
    viewModel { ArticlesViewModel(get()) }
    viewModel { HistoryExpensesViewModel(get(), get()) }
    viewModel { HistoryIncomeViewModel(get(), get()) }
    viewModel { CreateExpensesViewModel(get(), get(), get()) }

}
