package com.yusril.doaharian.ui.favorite

import androidx.lifecycle.ViewModel
import com.yusril.doaharian.core.data.DoaRepository
import com.yusril.doaharian.core.domain.usecase.DoaUseCase

class FavoriteViewModel(doaUseCase: DoaUseCase): ViewModel() {
    val favoriteDoa = doaUseCase.getFavoriteDoa()
}