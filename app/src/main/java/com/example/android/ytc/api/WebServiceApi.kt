package com.example.android.ytc.api

import com.example.android.ytc.data.model.ResponseMessage
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Web service API.
 * TODO: Replace with your API
 */
interface WebServiceApi {
    @GET("/say/hello")
    fun hello(@Query("name") name: String): Single<ResponseMessage>
}
