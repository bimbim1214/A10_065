package com.example.uas_pam.ui.viewmodel.aktivitas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_pam.model.AktivitasPertanian
import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.repository.AktivitasPertanianRepository
import com.example.uas_pam.repository.TanamanRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeAksUiState{
    data class Success(val aktivitasPertanian: List<AktivitasPertanian>): HomeAksUiState()
    object Error : HomeAksUiState()
    object Loading : HomeAksUiState()
}

class HomeAksViewModel(private val aks: AktivitasPertanianRepository): ViewModel(){
    var aksUiState: HomeAksUiState by mutableStateOf(HomeAksUiState.Loading)
        private set

    init {
        getAks()
    }

    fun getAks(){
        viewModelScope.launch {
            aksUiState = HomeAksUiState.Loading
            aksUiState = try {
                HomeAksUiState.Success(aks.getAktivitas().data)
            }catch (e: IOException){
                HomeAksUiState.Error
            }catch (e: HttpException){
                HomeAksUiState.Error
            }
        }
    }
    fun deleteAks(idAktivitas: String){
        viewModelScope.launch {
            try {
                aks.deleteAktivitas(idAktivitas)
            }catch (e: IOException){
                HomeAksUiState.Error
            }catch (e: HttpException){
                HomeAksUiState.Error
            }
        }
    }
}