package com.yusril.doaharian.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yusril.doaharian.core.data.local.entity.DoaEntity

@Dao
interface DoaDao {
    @Query("SELECT * FROM doaDB")
    fun getAllDoa(): LiveData<List<DoaEntity>>

    @Query("SELECT * FROM doaDB WHERE isFavorite = 1")
    fun getFavoriteDoa(): LiveData<List<DoaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoa(doa: List<DoaEntity>)

    @Update
    fun setFavoriteDoa(doa: DoaEntity)
}