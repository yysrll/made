package com.yusril.doaharian.core.utils

import com.yusril.doaharian.core.data.local.entity.DoaEntity
import com.yusril.doaharian.core.data.remote.response.DoaResponses
import com.yusril.doaharian.core.domain.model.Doa

object DataMapper {
    fun mapResponseToEntities(input: List<DoaResponses>): List<DoaEntity> {
        val doaList = ArrayList<DoaEntity>()
        input.map {
            val doa = DoaEntity(
                title = it.title,
                latin = it.latin,
                translation = it.translation,
                isFavorite = false
            )
            doaList.add(doa)
        }
        return doaList
    }

    fun mapEntitiesToDomain(input: List<DoaEntity>): List<Doa> =
        input.map {
            Doa(
                title = it.title,
                latin = it.latin,
                translation = it.translation,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntities(input: Doa) =
        DoaEntity(
            title = input.title,
            latin = input.latin,
            translation = input.translation,
            isFavorite = input.isFavorite
        )
}