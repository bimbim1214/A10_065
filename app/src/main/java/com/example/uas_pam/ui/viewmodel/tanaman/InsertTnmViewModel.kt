package com.example.uas_pam.ui.viewmodel.tanaman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.repository.TanamanRepository
import kotlinx.coroutines.launch

class InsertViewModel(private val tnm: TanamanRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertTnmState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertTnm(){
        viewModelScope.launch {
            try {
                tnm.insertTanaman(uiState.insertUiEvent.toTnm())
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}



data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id_tanaman :String = "",
    val nama_tanaman: String = "",
    val periode_tanam: String = "",
    val deskripsi_tanaman: String = "",
)

fun InsertUiEvent.toTnm(): Tanaman = Tanaman(
    id_tanaman = id_tanaman,
    nama_tanaman = nama_tanaman,
    periode_tanam = periode_tanam,
    deskripsi_tanaman = deskripsi_tanaman
)

fun Tanaman.toUiStateTnm(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

data class InserUievent(
    val inserUievent: InserUievent = InserUievent()
)

fun Tanaman.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_tanaman = id_tanaman,
    nama_tanaman = nama_tanaman,
    periode_tanam = periode_tanam,
    deskripsi_tanaman = deskripsi_tanaman
)
