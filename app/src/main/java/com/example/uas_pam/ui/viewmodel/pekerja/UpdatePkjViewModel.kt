package com.example.uas_pam.ui.viewmodel.pekerja

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.repository.PekerjaRepository
import com.example.uas_pam.ui.view.pekerja.DestinasiUpdatePekerja
import kotlinx.coroutines.launch

class UpdateViewModelPekerja (
    savedStateHandle: SavedStateHandle,
    private val pekerjaRepository: PekerjaRepository
) : ViewModel(){
    var updatePKJUiState by mutableStateOf(InsertPKJUiState())
        private set

    private val idPkj: String = checkNotNull(savedStateHandle[DestinasiUpdatePekerja.ID])

    init {
        viewModelScope.launch {
            updatePKJUiState = pekerjaRepository.getPekerjaById(idPkj)
                .toUiStatePkj()
        }
    }

    fun updateInsertPkjState(insertPKJEvent: InsertPKJEvent){
        updatePKJUiState = InsertPKJUiState(insertPKJUiEvent = insertPKJEvent)
    }

    suspend fun updatePkj(){
        viewModelScope.launch {
            try {
                pekerjaRepository.updatePekerja(idPkj, updatePKJUiState.insertPKJUiEvent.toPkj())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}