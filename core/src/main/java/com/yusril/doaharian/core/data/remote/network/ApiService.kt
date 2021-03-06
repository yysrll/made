package com.yusril.doaharian.core.data.remote.network

import com.yusril.doaharian.core.data.remote.response.ListDoaResponses
import retrofit2.http.GET

interface ApiService {
    @GET("api/data/json/doaharian")
    suspend fun getDoa(): ListDoaResponses
}