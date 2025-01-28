package com.example.uas_pam.ui.viewmodel.aktivitas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.repository.AktivitasPertanianRepository
import com.example.uas_pam.ui.view.aktivitas.DestinasiUpdateAktivitas
import kotlinx.coroutines.launch

class UpdateViewModelAks (
    saveStateHandle: SavedStateHandle,
    private val aktivitasPertanianRepository: AktivitasPertanianRepository
): ViewModel() {
    var updateAksUiState by mutableStateOf(InsertAksUiState())
        private set

    private val idAks: String = checkNotNull(saveStateHandle[DestinasiUpdateAktivitas.ID])

    init {
        viewModelScope.launch {
            updateAksUiState = aktivitasPertanianRepository.getAktivitasById(idAks)
                .toUiStateAks()
        }
    }

    fun updateInsertAksState(insertAksUiEvent: InsertAksUiEvent) {
        updateAksUiState = InsertAksUiState(insertAksUiEvent = insertAksUiEvent)
    }

    suspend fun updateAks(){
        viewModelScope.launch {
            try {
                aktivitasPertanianRepository.updateAktivitas(idAks, updateAksUiState.insertAksUiEvent.toAks())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}