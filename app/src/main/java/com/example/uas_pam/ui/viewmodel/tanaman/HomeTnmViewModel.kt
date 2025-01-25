package com.example.uas_pam.ui.viewmodel.tanaman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.repository.TanamanRepository
import kotlinx.coroutines.launch
import okio.IOException


sealed class HomeUiState{
    data class Success(val tanaman: List<Tanaman>): HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeViewModel(private val tnm: TanamanRepository): ViewModel(){
    var tnmUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getTnm()
    }

    fun getTnm(){
        viewModelScope.launch {
            tnmUiState = HomeUiState.Loading
            tnmUiState = try {
                HomeUiState.Success(tnm.getTanaman().data)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
    fun deleteTnm(id: String){
        viewModelScope.launch {
            try {
                tnm.deleteTanaman(id)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}