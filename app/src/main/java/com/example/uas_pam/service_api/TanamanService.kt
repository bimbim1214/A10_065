package com.example.uas_pam.service_api

import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.model.TanamanResponse
import com.example.uas_pam.model.TanamanResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TanamanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("tanaman")
    suspend fun getTanaman(): TanamanResponse

    @GET("tanaman/{id}")
    suspend fun getTanamanById(@Path("id") id:String):TanamanResponseDetail

    @POST("tanaman/tanaman")
    suspend fun insertTanaman(@Body tanaman: Tanaman)

    @PUT("tanaman/{id}")
    suspend fun updateTanaman(@Path("id") id: String, @Body tanaman: Tanaman)

    @DELETE("tanaman/{id}")
    suspend fun deleteTanaman(@Path("id") id: String):Response<Void>
}