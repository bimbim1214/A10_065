package com.example.uas_pam.ui.viewmodel.aktivitas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.AktivitasPertanian
import com.example.uas_pam.model.Pekerja
import com.example.uas_pam.model.PekerjaResponse
import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.model.TanamanResponse
import com.example.uas_pam.repository.AktivitasPertanianRepository
import com.example.uas_pam.repository.PekerjaRepository
import com.example.uas_pam.repository.TanamanRepository
import kotlinx.coroutines.launch


data class InsertAksUiState(
    val insertAksUiEvent: InsertAksUiEvent = InsertAksUiEvent()
)

data class InsertAksUiEvent(
    val id_aktivitas : String = "",
    val id_tanaman : String = "",
    val id_pekerja : String = "",
    val tanggal_aktivitas : String = "",
    val deskripsi_aktivitas : String = "",
)

fun InsertAksUiEvent.toAks(): AktivitasPertanian = AktivitasPertanian(
    id_aktivitas = id_aktivitas,
    id_tanaman = id_tanaman,
    id_pekerja = id_pekerja,
    tanggal_aktivitas = tanggal_aktivitas,
    deskripsi_aktivitas = deskripsi_aktivitas
)


fun AktivitasPertanian.toUiStateAks(): InsertAksUiState = InsertAksUiState(
    insertAksUiEvent = toInsertAktivitasUiEvent()
)

fun AktivitasPertanian.toInsertAktivitasUiEvent(): InsertAksUiEvent = InsertAksUiEvent(
    id_aktivitas = id_aktivitas,
    id_tanaman = id_tanaman,
    id_pekerja = id_pekerja,
    tanggal_aktivitas = tanggal_aktivitas,
    deskripsi_aktivitas = deskripsi_aktivitas
)