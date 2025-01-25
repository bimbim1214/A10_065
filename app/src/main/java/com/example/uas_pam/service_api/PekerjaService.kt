package com.example.uas_pam.service_api

import com.example.uas_pam.model.Pekerja
import com.example.uas_pam.model.PekerjaResponse
import com.example.uas_pam.model.PekerjaResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PekerjaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("pekerja")
    suspend fun getPekerja(): PekerjaResponse

    @GET("pekerja/{id}")
    suspend fun getPekerjaById(@Path("id") idpekerja: String): PekerjaResponseDetail

    @POST("pekerja/pekerja")
    suspend fun insertPekerja(@Body pekerja: Pekerja)

    @PUT("pekerja/{id}")
    suspend fun updatePekerja(@Path("id") idpekerja: String, @Body pekerja: Pekerja)

    @DELETE("pekerja/{id}")
    suspend fun deletePekerja(@Path("id") idpekerja: String): Response<Void>
}