package com.kg.pixabay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayApi {
    @GET("api/")
    fun getImages(
        @Query("q") searchWord: String,
        @Query("page")page: Int,
        @Query("per_page")perPage: Int = 20,
        @Query("key") key: String = "25567032-d0d60ad056929b8e1a0442943"
    ): Call<PixaModel>
}