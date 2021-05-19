package com.yusril.doaharian.core.di

import android.content.Context
import com.yusril.doaharian.core.data.DoaRepository
import com.yusril.doaharian.core.data.local.LocalDataSource
import com.yusril.doaharian.core.data.local.room.DoaDatabase
import com.yusril.doaharian.core.data.remote.RemoteDataSource
import com.yusril.doaharian.core.data.remote.network.ApiConfig
import com.yusril.doaharian.core.domain.usecase.DoaInteractor
import com.yusril.doaharian.core.domain.usecase.DoaUseCase
import com.yusril.doaharian.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): DoaRepository {
        val database = DoaDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.doaDao())
        val appExecutors = AppExecutors()

        return DoaRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideDoaUseCase(context: Context): DoaUseCase {
        val repository = provideRepository(context)
        return DoaInteractor(repository)
    }
}