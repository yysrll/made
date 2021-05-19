package com.yusril.doaharian.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.yusril.doaharian.core.data.local.LocalDataSource
import com.yusril.doaharian.core.data.local.entity.DoaEntity
import com.yusril.doaharian.core.data.remote.RemoteDataSource
import com.yusril.doaharian.core.data.remote.network.ApiResponse
import com.yusril.doaharian.core.data.remote.response.DoaResponses
import com.yusril.doaharian.core.domain.model.Doa
import com.yusril.doaharian.core.domain.repository.IDoaRepository
import com.yusril.doaharian.core.utils.AppExecutors
import com.yusril.doaharian.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DoaRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IDoaRepository{

    override fun getAllDoa(): Flow<Resource<List<Doa>>> =
        object : NetworkBoundResource<List<Doa>, List<DoaResponses>>() {
            override fun loadFromDB(): Flow<List<Doa>> {
                return localDataSource.getAllDoa().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Doa>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DoaResponses>>> =
                remoteDataSource.loadAllDoa()

            override suspend fun saveCallResult(data: List<DoaResponses>) {
                val doaList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertDoa(doaList)
            }

        }.asFlow()

    override fun getFavoriteDoa(): Flow<List<Doa>> =
        localDataSource.getFavoriteDoa().map { DataMapper.mapEntitiesToDomain(it) }

    override fun setFavoriteDoa(doa: Doa, state: Boolean) {
        val doaEntity = DataMapper.mapDomainToEntities(doa)
        appExecutors.diskIO().execute { localDataSource.setFavoriteDoa(doaEntity, state) }
    }

    companion object {
        @Volatile
        private var instance: DoaRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): DoaRepository =
            instance ?: synchronized(this) {
                instance ?: DoaRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }
}