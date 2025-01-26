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

