package com.example.uas_pam.ui.viewmodel.pekerja

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.Pekerja
import com.example.uas_pam.repository.PekerjaRepository
import kotlinx.coroutines.launch

class DetailPKJViewModel (private val pkjRepository: PekerjaRepository): ViewModel(){
    var DetailpkjUiState by mutableStateOf(DetailPekerjaUiState())
        private set

    fun fetchDetailPekerja(idpekerja: String){
        viewModelScope.launch {
            DetailpkjUiState = DetailPekerjaUiState(isLoading = true)
            try {
                val pekerja = pkjRepository.getPekerjaById(idpekerja)
                DetailpkjUiState = DetailPekerjaUiState(detailPKJEvent = pekerja.toDetailPekerjaUiEvent())
            }catch (e: Exception){
                e.printStackTrace()
                DetailpkjUiState = DetailPekerjaUiState(isError = true)
            }
        }
    }
}

data class DetailPekerjaUiState(
    val detailPKJEvent: InsertPKJEvent = InsertPKJEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventNotEmpty: Boolean
        get() = detailPKJEvent != InsertPKJEvent()
}

fun Pekerja.toDetailPekerjaUiEvent(): InsertPKJEvent{
    return InsertPKJEvent(
        id_pekerja = id_pekerja,
        nama_pekerja = nama_pekerja,
        jabatan = jabatan,
        kontak_pekerja = kontak_pekerja
    )
}