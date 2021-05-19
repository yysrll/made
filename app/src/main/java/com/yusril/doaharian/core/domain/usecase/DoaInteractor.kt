package com.yusril.doaharian.core.domain.usecase

import androidx.lifecycle.LiveData
import com.yusril.doaharian.core.data.DoaRepository
import com.yusril.doaharian.core.data.Resource
import com.yusril.doaharian.core.domain.model.Doa

class DoaInteractor(private val doaRepository: DoaRepository): DoaUseCase {
    override fun getAllDoa(): LiveData<Resource<List<Doa>>> = doaRepository.getAllDoa()

    override fun getFavoriteDoa(): LiveData<List<Doa>> = doaRepository.getFavoriteDoa()

    override fun setFavoriteDoa(doa: Doa, state: Boolean) = doaRepository.setFavoriteDoa(doa, state)
}