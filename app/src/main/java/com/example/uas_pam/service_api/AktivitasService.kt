package com.example.uas_pam.service_api

import com.example.uas_pam.model.AktivitasPertanian
import com.example.uas_pam.model.AktivitasPertanianResponse
import com.example.uas_pam.model.AktivitasPertanianResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AktivitasService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("aktivitasPertanian")
    suspend fun getAktivitas() : AktivitasPertanianResponse

    @GET("aktivitasPertanian/{id}")
    suspend fun getAktivitasById(@Path("id") idAktivitas:String): AktivitasPertanianResponseDetail

    @POST("aktivitasPertanian/aktivitasPertanian")
    suspend fun insertAktivitas(@Body aktivitasPertanian: AktivitasPertanian)

    @PUT("aktivitasPertanian/{id}")
    suspend fun updateAktivitas(@Path("id") idAktivitas: String, @Body aktivitasPertanian: AktivitasPertanian)

    @DELETE("aktivitasPertanian/{id}")
    suspend fun deleteAktivitas(@Path("id") idAktivitas: String): Response<Void>

}