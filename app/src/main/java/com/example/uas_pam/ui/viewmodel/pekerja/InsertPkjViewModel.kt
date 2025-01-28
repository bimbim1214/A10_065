package com.example.uas_pam.ui.viewmodel.pekerja

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.Pekerja
import com.example.uas_pam.repository.PekerjaRepository
import kotlinx.coroutines.launch


fun Pekerja.toInsertPekerjaUiEvent(): InsertPKJEvent = InsertPKJEvent(
    id_pekerja = id_pekerja,
    nama_pekerja = nama_pekerja,
    jabatan = jabatan,
    kontak_pekerja = kontak_pekerja
)