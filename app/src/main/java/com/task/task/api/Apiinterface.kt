package com.task.task.api

import com.task.task.api.model.VendarDetails
import retrofit2.http.GET
import retrofit2.http.Query


interface Apiinterface
{
         @GET("api/v2/vendor-details")
      suspend  fun getData(
            @Query("id") id: Int,
            @Query("lang") lang: String,
            @Query("user_id") userId: String
        ):VendarDetails


}