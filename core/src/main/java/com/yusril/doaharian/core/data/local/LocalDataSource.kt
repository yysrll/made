package com.yusril.doaharian.core.data.local

import com.yusril.doaharian.core.data.local.entity.DoaEntity
import com.yusril.doaharian.core.data.local.room.DoaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val doaDao: DoaDao) {

    fun getAllDoa(): Flow<List<DoaEntity>> = doaDao.getAllDoa()

    fun getFavoriteDoa(): Flow<List<DoaEntity>> = doaDao.getFavoriteDoa()

    suspend fun insertDoa(doa: List<DoaEntity>) = doaDao.insertDoa(doa)

    fun setFavoriteDoa(doa: DoaEntity, state: Boolean) {
        doa.isFavorite = state
        doaDao.setFavoriteDoa(doa)
    }

}