package com.yusril.doaharian.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yusril.doaharian.core.data.DoaRepository
import com.yusril.doaharian.core.domain.usecase.DoaUseCase

class HomeViewModel(doaUseCase: DoaUseCase): ViewModel() {

    val doa = doaUseCase.getAllDoa().asLiveData()
}