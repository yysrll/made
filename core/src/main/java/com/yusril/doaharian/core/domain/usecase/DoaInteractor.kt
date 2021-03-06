package com.yusril.doaharian.core.domain.usecase

import com.yusril.doaharian.core.data.Resource
import com.yusril.doaharian.core.domain.model.Doa
import com.yusril.doaharian.core.domain.repository.IDoaRepository
import kotlinx.coroutines.flow.Flow

class DoaInteractor(private val doaRepository: IDoaRepository) : DoaUseCase {
    override fun getAllDoa(): Flow<Resource<List<Doa>>> = doaRepository.getAllDoa()

    override fun getFavoriteDoa(): Flow<List<Doa>> = doaRepository.getFavoriteDoa()

    override fun setFavoriteDoa(doa: Doa, state: Boolean) = doaRepository.setFavoriteDoa(doa, state)
}