package com.khiemle.nab.presentation.deps

import androidx.lifecycle.ViewModel
import com.khiemle.nab.presentation.MainViewModel
import com.khiemle.utilities.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}