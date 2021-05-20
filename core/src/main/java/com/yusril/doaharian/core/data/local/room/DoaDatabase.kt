package com.yusril.doaharian.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yusril.doaharian.core.data.local.entity.DoaEntity

@Database(entities = [DoaEntity::class], version = 1, exportSchema = false)
abstract class DoaDatabase : RoomDatabase() {

    abstract fun doaDao(): DoaDao

}