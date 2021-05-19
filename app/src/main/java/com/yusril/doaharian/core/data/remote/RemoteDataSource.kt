package com.yusril.doaharian.core.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yusril.doaharian.core.data.remote.network.ApiResponse
import com.yusril.doaharian.core.data.remote.network.ApiService
import com.yusril.doaharian.core.data.remote.response.DoaResponses
import com.yusril.doaharian.core.data.remote.response.ListDoaResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {

    fun loadAllDoa(): LiveData<ApiResponse<List<DoaResponses>>> {
        val result = MutableLiveData<ApiResponse<List<DoaResponses>>>()

        val client = apiService.getDoa()

        client.enqueue(object : Callback<ListDoaResponses>{
            override fun onResponse(
                call: Call<ListDoaResponses>,
                response: Response<ListDoaResponses>
            ) {
                val dataArray = response.body()?.result?.data
                Log.d("response body", response.body().toString())
                Log.d("response", dataArray.toString())
                result.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListDoaResponses>, t: Throwable) {
                result.value = ApiResponse.Error(t.message.toString())
            }

        })
        return result
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