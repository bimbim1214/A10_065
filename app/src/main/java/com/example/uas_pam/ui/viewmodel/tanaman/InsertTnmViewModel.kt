package com.example.uas_pam.ui.viewmodel.tanaman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.repository.TanamanRepository
import kotlinx.coroutines.launch


fun InsertUiEvent.toTnm(): Tanaman = Tanaman(
    id_tanaman = id_tanaman,
    nama_tanaman = nama_tanaman,
    periode_tanam = periode_tanam,
    deskripsi_tanaman = deskripsi_tanaman
)

fun Tanaman.toUiStateTnm(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

data class InserUievent(
    val inserUievent: InserUievent = InserUievent()
)

fun Tanaman.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_tanaman = id_tanaman,
    nama_tanaman = nama_tanaman,
    periode_tanam = periode_tanam,
    deskripsi_tanaman = deskripsi_tanaman
)
