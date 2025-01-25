package com.example.uas_pam.ui.viewmodel.tanaman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.repository.TanamanRepository
import com.example.uas_pam.ui.view.tanaman.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdateTnmViewModel (
    saveStateHandle: SavedStateHandle,
    private val tanamanRepository: TanamanRepository
): ViewModel() {
    var updaUiState by mutableStateOf(InsertUiState())
        private set

    private val idTnm: String = checkNotNull(saveStateHandle[DestinasiUpdate.ID])

    init {
        viewModelScope.launch {
            updaUiState = tanamanRepository.getTanamanById(idTnm)
                .toUiStateTnm()
        }
    }

    fun updateInsertTnmState(insertUiEvent: InsertUiEvent) {
        updaUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateTnm(){
        viewModelScope.launch {
            try {
                tanamanRepository.updateTanaman(idTnm, updaUiState.insertUiEvent.toTnm())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}