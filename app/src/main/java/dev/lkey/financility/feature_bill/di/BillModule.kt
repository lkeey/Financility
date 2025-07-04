package dev.lkey.financility.feature_bill.di

import dev.lkey.financility.feature_bill.data.repository.BillRepositoryImpl
import dev.lkey.financility.feature_bill.domain.repository.BillRepository
import dev.lkey.financility.feature_bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.financility.feature_bill.domain.usecase.UpdateBillUseCase
import dev.lkey.financility.feature_bill.presentation.current.viewmodel.BillViewModel
import dev.lkey.financility.feature_bill.presentation.edit.viewmodel.EditBillViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val billModule = module {

    // Репозиторий
    single<BillRepository> { BillRepositoryImpl() }

    // Use-case
    single { GetBillInfoUseCase(get()) }
    factory { UpdateBillUseCase(get()) }

    // ViewModel
    viewModel { BillViewModel(get(), get()) }
    viewModel { EditBillViewModel(get(), get()) }

}