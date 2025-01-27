package com.example.uas_pam.di

import com.example.uas_pam.repository.AktivitasPertanianRepository
import com.example.uas_pam.repository.CatatanPanenRepository
import com.example.uas_pam.repository.NetworkAktivitasRepository
import com.example.uas_pam.repository.NetworkCatatanPanenRepository
import com.example.uas_pam.repository.NetworkPekerjaRepository
import com.example.uas_pam.repository.NetworkTanamanRepository
import com.example.uas_pam.repository.PekerjaRepository
import com.example.uas_pam.repository.TanamanRepository
import com.example.uas_pam.service_api.AktivitasService
import com.example.uas_pam.service_api.PanenService
import com.example.uas_pam.service_api.PekerjaService
import com.example.uas_pam.service_api.TanamanService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

interface AppContainer{
    val tanamanRepository: TanamanRepository
    val pekerjaRepository: PekerjaRepository
    val aktivitasPertanianRepository: AktivitasPertanianRepository
    val catatanPanenRepository: CatatanPanenRepository
}
