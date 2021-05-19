package com.yusril.doaharian.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doa(
    val title: String,
    val latin: String,
    val translation: String,
    val isFavorite: Boolean
) : Parcelable
