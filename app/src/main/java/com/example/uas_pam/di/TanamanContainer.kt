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
class KebunContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()


    private val tanamanService : TanamanService by lazy {
        retrofit.create(TanamanService::class.java)
    }
    private val pekerjaService: PekerjaService by lazy {
        retrofit.create(PekerjaService::class.java)
    }
    private val aktivitasService: AktivitasService by lazy {
        retrofit.create(AktivitasService::class.java)
    }
    private val panenService: PanenService by lazy {
        retrofit.create(PanenService::class.java)
    }


    override val tanamanRepository: TanamanRepository by lazy {
        NetworkTanamanRepository(tanamanService)
    }
    override val pekerjaRepository: PekerjaRepository by lazy {
        NetworkPekerjaRepository(pekerjaService)
    }
    override val aktivitasPertanianRepository: AktivitasPertanianRepository by lazy {
        NetworkAktivitasRepository(aktivitasService)
    }
    override val catatanPanenRepository: CatatanPanenRepository by lazy {
        NetworkCatatanPanenRepository(panenService)
    }
}
