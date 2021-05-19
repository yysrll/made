package com.yusril.doaharian.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListDoaResponses(

    @field:SerializedName("result")
    val result: DataResponse
)

data class DataResponse(

    @field:SerializedName("data")
    val data: MutableList<DoaResponses>
)

data class DoaResponses(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("latin")
    val latin: String,

    @field:SerializedName("translation")
    val translation: String,

)
