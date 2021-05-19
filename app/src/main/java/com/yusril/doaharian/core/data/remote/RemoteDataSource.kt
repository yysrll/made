package com.yusril.doaharian.core.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yusril.doaharian.core.data.remote.network.ApiResponse
import com.yusril.doaharian.core.data.remote.network.ApiService
import com.yusril.doaharian.core.data.remote.response.DoaResponses
import com.yusril.doaharian.core.data.remote.response.ListDoaResponses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {

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

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }
}