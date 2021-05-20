package com.yusril.doaharian.favorite.di

import com.yusril.doaharian.favorite.ui.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}