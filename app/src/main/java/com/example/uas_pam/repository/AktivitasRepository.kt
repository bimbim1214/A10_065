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

