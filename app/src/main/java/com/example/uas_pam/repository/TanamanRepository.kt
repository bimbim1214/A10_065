package com.example.uas_pam.repository

import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.model.TanamanResponse
import com.example.uas_pam.service_api.TanamanService
import okio.IOException


interface TanamanRepository{
    suspend fun getTanaman(): TanamanResponse
    suspend fun insertTanaman(tanaman: Tanaman)
    suspend fun updateTanaman(id: String, tanaman: Tanaman)
    suspend fun deleteTanaman(id: String)
    suspend fun getTanamanById(id: String): Tanaman
}

class NetworkTanamanRepository(
    private val tanamanApiService: TanamanService
): TanamanRepository {
    override suspend fun insertTanaman(tanaman: Tanaman) {
        tanamanApiService.insertTanaman(tanaman)
    }

    override suspend fun updateTanaman(id: String, tanaman: Tanaman) {
        tanamanApiService.updateTanaman(id, tanaman)
    }

    override suspend fun deleteTanaman(id: String) {
        try {
            val response = tanamanApiService.deleteTanaman(id)
            if (!response.isSuccessful){
                throw IOException("Failed to delete tanaman. HTTP Status code: "+
                "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getTanaman(): TanamanResponse {
        return tanamanApiService.getTanaman()
    }

    override suspend fun getTanamanById(id: String): Tanaman {
        return tanamanApiService.getTanamanById(id).data
    }
}