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

