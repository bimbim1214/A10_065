package com.example.uas_pam.ui.viewmodel.catatanpanen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.CatatanPanen
import com.example.uas_pam.repository.CatatanPanenRepository
import kotlinx.coroutines.launch

class InsertPanenViewModel(
    private val pnn: CatatanPanenRepository,
    ): ViewModel(){


    var pnnuiState by mutableStateOf(InsertPnnUiState())
        private set


    fun updateInsertPanenState(insertPnnUiEvent: InsertPnnUiEvent){
        pnnuiState = InsertPnnUiState(insertPnnUiEvent = insertPnnUiEvent)
    }

    suspend fun insertPnn(){
        viewModelScope.launch {
            try {
                pnn.insertPanen(pnnuiState.insertPnnUiEvent.toPnn())
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertPnnUiState(
    val insertPnnUiEvent: InsertPnnUiEvent = InsertPnnUiEvent()
)

data class InsertPnnUiEvent(
    val id_panen : String = "",
    val id_tanaman : String = "",
    val tanggal_panen : String = "",
    val jumlah_panen: String = "",
    val keterangan : String = "",
)

fun InsertPnnUiEvent.toPnn(): CatatanPanen = CatatanPanen(
    id_panen = id_panen,
    id_tanaman = id_tanaman,
    tanggal_panen = tanggal_panen,
    jumlah_panen = jumlah_panen,
    keterangan = keterangan
)


fun CatatanPanen.toUiStatePnn(): InsertPnnUiState = InsertPnnUiState(
    insertPnnUiEvent = toInsertPanenUiEvent()
)

fun CatatanPanen.toInsertPanenUiEvent(): InsertPnnUiEvent = InsertPnnUiEvent(
    id_panen = id_panen,
    id_tanaman = id_tanaman,
    tanggal_panen = tanggal_panen,
    jumlah_panen = jumlah_panen,
    keterangan = keterangan
)