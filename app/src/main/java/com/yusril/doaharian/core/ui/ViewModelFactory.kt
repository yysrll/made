package com.yusril.doaharian.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yusril.doaharian.core.data.DoaRepository
import com.yusril.doaharian.core.di.Injection
import com.yusril.doaharian.core.domain.usecase.DoaUseCase
import com.yusril.doaharian.ui.detail.DetailViewModel
import com.yusril.doaharian.ui.favorite.FavoriteViewModel
import com.yusril.doaharian.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val doaUseCase: DoaUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(doaUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(doaUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(doaUseCase) as T
            }
            else -> throw Throwable("Unknown viewModel class : " +modelClass.name)
        }


    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideDoaUseCase(context))
            }
    }
}