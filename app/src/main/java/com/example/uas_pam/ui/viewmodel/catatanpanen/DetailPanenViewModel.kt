package com.example.uas_pam.ui.viewmodel.catatanpanen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.AktivitasPertanian
import com.example.uas_pam.model.CatatanPanen
import com.example.uas_pam.repository.AktivitasPertanianRepository
import com.example.uas_pam.repository.CatatanPanenRepository
import com.example.uas_pam.ui.viewmodel.aktivitas.DetailAktivitasUiState
import com.example.uas_pam.ui.viewmodel.aktivitas.toDetailAktivitasUiEvent
import kotlinx.coroutines.launch

class DetailPanenViewModel (private val pnnRepository: CatatanPanenRepository) : ViewModel(){
    var DetailPnnUiState by mutableStateOf(DetailPanenUiState())
        private set

    fun fetchDetailPanen(idPanen: String){
        viewModelScope.launch {
            DetailPnnUiState = DetailPanenUiState(isLoading = true)
            try {
                val aktivitas = pnnRepository.getPanenById(idPanen)
                DetailPnnUiState = DetailPanenUiState(detailPnnEvent = aktivitas.toDetailPanenUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                DetailPnnUiState = DetailPanenUiState(isError = true,  errorMessage = "Failes to fetch detail: ${e.message}")
            }
        }
    }
}

data class DetailPanenUiState(
    val  detailPnnEvent: InsertPnnUiEvent = InsertPnnUiEvent(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventNotEmpty: Boolean
        get() = detailPnnEvent != InsertPnnUiEvent()
}

fun CatatanPanen.toDetailPanenUiEvent(): InsertPnnUiEvent {
    return InsertPnnUiEvent(
        id_panen = id_panen,
        id_tanaman = id_tanaman,
        tanggal_panen = tanggal_panen,
        jumlah_panen = jumlah_panen,
        keterangan = keterangan
    )
}