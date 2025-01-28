package com.example.uas_pam.ui.viewmodel.catatanpanen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.repository.CatatanPanenRepository
import com.example.uas_pam.ui.view.catatanpanen.DestinasiUpdatePanen
import kotlinx.coroutines.launch

class UpdatePanenViewModel (
    saveStateHandle: SavedStateHandle,
    private val catatanPanenRepository: CatatanPanenRepository
): ViewModel() {
    var updatePnnUiState by mutableStateOf(InsertPnnUiState())
        private set

    private val idPnn: String = checkNotNull(saveStateHandle[DestinasiUpdatePanen.ID])

    init {
        viewModelScope.launch {
            updatePnnUiState = catatanPanenRepository.getPanenById(idPnn)
                .toUiStatePnn()
        }
    }

    fun updateInsertPanenState(insertPnnUiEvent: InsertPnnUiEvent) {
        updatePnnUiState = InsertPnnUiState(insertPnnUiEvent = insertPnnUiEvent)
    }

    suspend fun updatePnn(){
        viewModelScope.launch {
            try {
                catatanPanenRepository.updatePanen(idPnn, updatePnnUiState.insertPnnUiEvent.toPnn())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}