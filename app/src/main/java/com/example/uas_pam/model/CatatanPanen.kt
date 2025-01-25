package com.example.uas_pam.model

import kotlinx.serialization.Serializable

@Serializable
data class CatatanPanenResponse(
    val status: Boolean,
    val message: String,
    val data: List<CatatanPanen>
)

@Serializable
data class CatatanPanenResponseDetail(
    val status: Boolean,
    val message: String,
    val data: CatatanPanen
)

@Serializable
data class CatatanPanen(
    val id_panen: String,
    val id_tanaman: String,
    val tanggal_panen: String,
    val jumlah_panen: String,
    val keterangan: String
)
