package com.yusril.doaharian.core.data.local

import androidx.lifecycle.LiveData
import com.yusril.doaharian.core.data.local.entity.DoaEntity
import com.yusril.doaharian.core.data.local.room.DoaDao

class LocalDataSource private constructor(private val doaDao: DoaDao) {

    fun getAllDoa(): LiveData<List<DoaEntity>> = doaDao.getAllDoa()

    fun getFavoriteDoa(): LiveData<List<DoaEntity>> = doaDao.getFavoriteDoa()

    fun insertDoa(doa: List<DoaEntity>) = doaDao.insertDoa(doa)

    fun setFavoriteDoa(doa: DoaEntity, state: Boolean) {
        doa.isFavorite = state
        doaDao.setFavoriteDoa(doa)
    }

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(doaDao: DoaDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(doaDao)
            }
    }

}