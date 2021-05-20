package com.yusril.doaharian.ui.detail

import androidx.lifecycle.ViewModel
import com.yusril.doaharian.core.domain.model.Doa
import com.yusril.doaharian.core.domain.usecase.DoaUseCase

class DetailViewModel(private val doaUseCase: DoaUseCase) : ViewModel() {

    fun setFavoriteDoa(doa: Doa, newState: Boolean) = doaUseCase.setFavoriteDoa(doa, newState)
}