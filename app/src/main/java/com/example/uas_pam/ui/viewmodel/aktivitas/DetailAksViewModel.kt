package com.example.uas_pam.ui.viewmodel.aktivitas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.AktivitasPertanian
import com.example.uas_pam.repository.AktivitasPertanianRepository
import kotlinx.coroutines.launch

class DetailAksViewModel (private val aksRepository: AktivitasPertanianRepository) : ViewModel(){
    var DetailaksuiState by mutableStateOf(DetailAktivitasUiState())
        private set

    fun fetchDetailAktivitas(idAktivitas: String){
        viewModelScope.launch {
            DetailaksuiState = DetailAktivitasUiState(isLoading = true)
            try {
                val aktivitas = aksRepository.getAktivitasById(idAktivitas)
                DetailaksuiState = DetailAktivitasUiState(detailAksEvent = aktivitas.toDetailAktivitasUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                DetailaksuiState = DetailAktivitasUiState(isError = true,  errorMessage = "Failes to fetch detail: ${e.message}")
            }
        }
    }
}

data class DetailAktivitasUiState(
    val  detailAksEvent: InsertAksUiEvent = InsertAksUiEvent(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventNotEmpty: Boolean
        get() = detailAksEvent != InsertAksUiEvent()
}

fun AktivitasPertanian.toDetailAktivitasUiEvent(): InsertAksUiEvent {
    return InsertAksUiEvent(
        id_aktivitas = id_aktivitas,
        id_tanaman = id_tanaman,
        id_pekerja = id_pekerja,
        tanggal_aktivitas = tanggal_aktivitas,
        deskripsi_aktivitas = deskripsi_aktivitas
    )
}