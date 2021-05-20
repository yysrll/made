package com.yusril.doaharian.di

import com.yusril.doaharian.core.domain.usecase.DoaInteractor
import com.yusril.doaharian.core.domain.usecase.DoaUseCase
import com.yusril.doaharian.ui.detail.DetailViewModel
import com.yusril.doaharian.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<DoaUseCase> { DoaInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}