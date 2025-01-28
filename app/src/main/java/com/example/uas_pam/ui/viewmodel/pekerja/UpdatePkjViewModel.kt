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


}