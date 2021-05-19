package com.yusril.doaharian.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yusril.doaharian.core.data.local.entity.DoaEntity

@Database(entities = [DoaEntity::class], version = 1, exportSchema = false)
abstract class DoaDatabase : RoomDatabase() {

    abstract fun doaDao(): DoaDao

    companion object {
        @Volatile
        private var INSTANCE: DoaDatabase? = null

        fun getInstance(context: Context): DoaDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DoaDatabase::class.java,
                    "DoaDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}