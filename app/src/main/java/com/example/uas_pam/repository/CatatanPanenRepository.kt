package com.example.uas_pam.repository

import com.example.uas_pam.model.CatatanPanen
import com.example.uas_pam.model.CatatanPanenResponse
import com.example.uas_pam.service_api.PanenService
import okio.IOException

interface CatatanPanenRepository {
    suspend fun getPanen(): CatatanPanenResponse
    suspend fun insertPanen(catatanPanen: CatatanPanen)
    suspend fun updatePanen(idPanen: String, catatanPanen: CatatanPanen)
    suspend fun deletePanen(idPanen: String)
    suspend fun getPanenById(idPanen: String): CatatanPanen
}

class NetworkCatatanPanenRepository(
    private val panenApiService: PanenService
): CatatanPanenRepository {
    override suspend fun insertPanen(catatanPanen: CatatanPanen) {
        panenApiService.insertPanen(catatanPanen)
    }

    override suspend fun updatePanen(idPanen: String, catatanPanen: CatatanPanen) {
        panenApiService.updatePanen(idPanen, catatanPanen)
    }

    override suspend fun deletePanen(idPanen: String) {
        try {
            val response = panenApiService.deletePanen(idPanen)
            if (!response.isSuccessful){
                throw IOException("Failled to delete Panen. HTTP Status code: "+
                "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun getPanen(): CatatanPanenResponse {
        return panenApiService.getPanen()
    }

    override suspend fun getPanenById(idPanen: String): CatatanPanen {
        return panenApiService.getPanenById(idPanen).data
    }
}