package com.yusril.doaharian.core.data.remote


import com.yusril.doaharian.core.data.remote.network.ApiResponse
import com.yusril.doaharian.core.data.remote.network.ApiService
import com.yusril.doaharian.core.data.remote.response.DoaResponses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun loadAllDoa(): Flow<ApiResponse<List<DoaResponses>>> {
        return flow {
            try {
                val response = apiService.getDoa()
                val dataArray = response.result.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.result.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}