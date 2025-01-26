package com.example.uas_pam.repository

import com.example.uas_pam.model.Pekerja
import com.example.uas_pam.model.PekerjaResponse
import com.example.uas_pam.service_api.PekerjaService
import okio.IOException

interface PekerjaRepository{
    suspend fun getPekerja(): PekerjaResponse
    suspend fun insertPekerja(pekerja: Pekerja)
    suspend fun updatePekerja(idpekerja: String, pekerja: Pekerja)
    suspend fun deletePekerja(idpekerja: String)
    suspend fun getPekerjaById(idpekerja: String): Pekerja
}

class NetworkPekerjaRepository(
    private val pekerjeApiService: PekerjaService
): PekerjaRepository {
    override suspend fun insertPekerja(pekerja: Pekerja) {
        pekerjeApiService.insertPekerja(pekerja)
    }

    override suspend fun updatePekerja(idpekerja: String, pekerja: Pekerja) {
        pekerjeApiService.updatePekerja(idpekerja, pekerja)
    }

    override suspend fun deletePekerja(idpekerja: String) {
        try {
            val response = pekerjeApiService.deletePekerja(idpekerja)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete pekerja. HTTP Status code: +" +
                            "${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPekerja(): PekerjaResponse {
        return pekerjeApiService.getPekerja()
    }

    override suspend fun getPekerjaById(idpekerja: String): Pekerja {
        return pekerjeApiService.getPekerjaById(idpekerja).data
    }
}