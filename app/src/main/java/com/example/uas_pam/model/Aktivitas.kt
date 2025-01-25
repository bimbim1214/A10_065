package com.example.uas_pam.model

import kotlinx.serialization.Serializable

@Serializable
data class AktivitasPertanianResponse(
    val status: Boolean,
    val message: String,
    val data: List<AktivitasPertanian>
)

@Serializable
data class AktivitasPertanianResponseDetail(
    val status: Boolean,
    val message: String,
    val data: AktivitasPertanian
)

@Serializable
data class AktivitasPertanian(
    val id_aktivitas: String,
    val id_tanaman: String,
    val id_pekerja: String,
    val tanggal_aktivitas: String,
    val deskripsi_aktivitas: String
)