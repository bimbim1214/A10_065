package com.example.uas_pam.service_api

import com.example.uas_pam.model.CatatanPanen
import com.example.uas_pam.model.CatatanPanenResponse
import com.example.uas_pam.model.CatatanPanenResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PanenService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("catatanPanen")
    suspend fun getPanen(): CatatanPanenResponse

    @GET("catatanPanen/{id}")
    suspend fun getPanenById(@Path("id") idPanen: String): CatatanPanenResponseDetail

    @POST("catatanPanen/catatanPanen")
    suspend fun insertPanen(@Body catatanPanen: CatatanPanen)

    @PUT("catatanPanen/{id}")
    suspend fun updatePanen(@Path("id") idPanen: String, @Body catatanPanen: CatatanPanen)

    @DELETE("catatanPanen/{id}")
    suspend fun deletePanen(@Path("id") idPanen: String):Response<Void>
}