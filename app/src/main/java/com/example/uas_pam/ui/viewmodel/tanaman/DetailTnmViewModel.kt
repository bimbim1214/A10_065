package com.example.uas_pam.ui.viewmodel.tanaman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.repository.TanamanRepository
import kotlinx.coroutines.launch


class DetailViewModel (private val tnmRepository: TanamanRepository) : ViewModel(){
    var uiState by mutableStateOf(DetailTanamanUiState())
        private set

    fun fetchDetailTanaman(id: String){
        viewModelScope.launch {
            uiState = DetailTanamanUiState(isLoading = true)
            try {
                val tanaman = tnmRepository.getTanamanById(id)
                uiState = DetailTanamanUiState(detailEvent = tanaman.toDetailTanamanUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailTanamanUiState(isError = true,  errorMessage = "Failes to fetch detail: ${e.message}")
            }
        }
    }
}

data class DetailTanamanUiState(
    val detailEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
){
    val isUiEventNotEmpty: Boolean
        get() = detailEvent != InsertUiEvent()
}

fun Tanaman.toDetailTanamanUiEvent(): InsertUiEvent {
    return InsertUiEvent(
        id_tanaman = id_tanaman,
        nama_tanaman = nama_tanaman,
        periode_tanam = periode_tanam,
        deskripsi_tanaman = deskripsi_tanaman
    )
}