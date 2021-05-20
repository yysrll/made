package com.yusril.doaharian.core.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "doaDB")
data class DoaEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo
    var title: String,

    @ColumnInfo
    var latin: String,

    @ColumnInfo
    var translation: String,

    @ColumnInfo
    var isFavorite: Boolean,


    ) : Parcelable
