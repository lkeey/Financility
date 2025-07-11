package dev.lkey.bill.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.bill.presentation.current.viewmodel.BillViewModel
import dev.lkey.bill.presentation.edit.viewmodel.EditBillViewModel
import dev.lkey.core.di.utils.ViewModelKey

/**
 * Модуль VM счета
 * */

@Module
interface BillViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BillViewModel::class)
    fun bindBillViewModel(viewModel: BillViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditBillViewModel::class)
    fun bindEditBillViewModel(viewModel: EditBillViewModel): ViewModel

}
