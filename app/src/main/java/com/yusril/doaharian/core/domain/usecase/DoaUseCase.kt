package com.yusril.doaharian.core.domain.usecase

import androidx.lifecycle.LiveData
import com.yusril.doaharian.core.data.Resource
import com.yusril.doaharian.core.domain.model.Doa

interface DoaUseCase {

    fun getAllDoa(): LiveData<Resource<List<Doa>>>

    fun getFavoriteDoa(): LiveData<List<Doa>>

    fun setFavoriteDoa(doa: Doa, state: Boolean)
}