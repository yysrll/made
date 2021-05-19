package com.yusril.doaharian.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yusril.doaharian.core.data.local.entity.DoaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DoaDao {
    @Query("SELECT * FROM doaDB")
    fun getAllDoa(): Flow<List<DoaEntity>>

    @Query("SELECT * FROM doaDB WHERE isFavorite = 1")
    fun getFavoriteDoa(): Flow<List<DoaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoa(doa: List<DoaEntity>)

    @Update
    fun setFavoriteDoa(doa: DoaEntity)
}