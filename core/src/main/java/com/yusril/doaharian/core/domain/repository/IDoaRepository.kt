package com.yusril.doaharian.core.domain.repository

import androidx.lifecycle.LiveData
import com.yusril.doaharian.core.data.Resource
import com.yusril.doaharian.core.domain.model.Doa
import kotlinx.coroutines.flow.Flow

interface IDoaRepository {

    fun getAllDoa(): Flow<Resource<List<Doa>>>

    fun getFavoriteDoa(): Flow<List<Doa>>

    fun setFavoriteDoa(doa: Doa, state: Boolean)
}