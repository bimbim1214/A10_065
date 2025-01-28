package com.example.uas_pam.ui.viewmodel.pekerja

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.Pekerja
import com.example.uas_pam.repository.PekerjaRepository
import kotlinx.coroutines.launch




data class InsertPKJUiState(
    val insertPKJUiEvent: InsertPKJEvent = InsertPKJEvent()
)

data class InsertPKJEvent(
    val id_pekerja: String = "",
    val nama_pekerja: String = "",
    val jabatan: String = "",
    val kontak_pekerja: String = "",
)

fun InsertPKJEvent.toPkj(): Pekerja = Pekerja(
    id_pekerja = id_pekerja,
    nama_pekerja = nama_pekerja,
    jabatan = jabatan,
    kontak_pekerja = kontak_pekerja
)

fun Pekerja.toUiStatePkj(): InsertPKJUiState = InsertPKJUiState(
    insertPKJUiEvent = toInsertPekerjaUiEvent()
)

data class InserPKJEvent(
    val insertPKJEvent: InsertPKJEvent = InsertPKJEvent()
)

fun Pekerja.toInsertPekerjaUiEvent(): InsertPKJEvent = InsertPKJEvent(
    id_pekerja = id_pekerja,
    nama_pekerja = nama_pekerja,
    jabatan = jabatan,
    kontak_pekerja = kontak_pekerja
)