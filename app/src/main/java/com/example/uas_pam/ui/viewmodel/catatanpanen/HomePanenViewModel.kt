package com.example.uas_pam.ui.viewmodel.catatanpanen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_pam.model.CatatanPanen
import com.example.uas_pam.repository.CatatanPanenRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomePanenUiState{
    data class Success(val catatanPanen: List<CatatanPanen>): HomePanenUiState()
    object Error : HomePanenUiState()
    object Loading : HomePanenUiState()
}

