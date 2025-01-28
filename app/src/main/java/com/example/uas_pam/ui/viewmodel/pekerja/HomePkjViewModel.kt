package com.example.uas_pam.ui.viewmodel.pekerja

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_pam.model.Pekerja
import com.example.uas_pam.repository.PekerjaRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomePKJUiState{
    data class Success(val pekerja: List<Pekerja>): HomePKJUiState()
    object Error: HomePKJUiState()
    object Loading: HomePKJUiState()
}

