package com.example.uas_pam.repository

import com.example.uas_pam.model.AktivitasPertanian
import com.example.uas_pam.model.AktivitasPertanianResponse
import com.example.uas_pam.service_api.AktivitasService
import okio.IOException

interface AktivitasPertanianRepository{
    suspend fun getAktivitas(): AktivitasPertanianResponse
    suspend fun insertAktivitas(aktivitasPertanian: AktivitasPertanian)
    suspend fun updateAktivitas(idAktivitas: String, aktivitasPertanian: AktivitasPertanian)
    suspend fun deleteAktivitas(idAktivitas: String)
    suspend fun getAktivitasById(idAktivitas: String): AktivitasPertanian
}

class NetworkAktivitasRepository(
    private val aktivitasService: AktivitasService
): AktivitasPertanianRepository{
    override suspend fun insertAktivitas(aktivitasPertanian: AktivitasPertanian) {
        aktivitasService.insertAktivitas(aktivitasPertanian)
    }

    override suspend fun updateAktivitas(
        idAktivitas: String,
        aktivitasPertanian: AktivitasPertanian
    ) {
        aktivitasService.updateAktivitas(idAktivitas, aktivitasPertanian)
    }

    override suspend fun deleteAktivitas(idAktivitas: String) {
        try {
            val response = aktivitasService.deleteAktivitas(idAktivitas)
            if (!response.isSuccessful){
                throw IOException("Failled to delete aktivitas. HTTP Status code: "+
                "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun getAktivitas(): AktivitasPertanianResponse {
        return aktivitasService.getAktivitas()
    }

    override suspend fun getAktivitasById(idAktivitas: String): AktivitasPertanian {
        return aktivitasService.getAktivitasById(idAktivitas).data
    }
}