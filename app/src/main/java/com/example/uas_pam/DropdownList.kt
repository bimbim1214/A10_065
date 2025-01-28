package com.example.uas_pam

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam.ui.viewmodel.pekerja.HomePKJUiState
import com.example.uas_pam.ui.viewmodel.pekerja.HomePKJViewModel
import com.example.uas_pam.ui.viewmodel.tanaman.HomeUiState
import com.example.uas_pam.ui.viewmodel.tanaman.HomeViewModel

object TanamanList {
    @Composable
    fun DataNamaTanaman(
        tanamanViewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<Pair<String, String>> {
        val tnmUiState = tanamanViewModel.tnmUiState
        return when (tnmUiState) {
            is HomeUiState.Success -> tnmUiState.tanaman.map { it.id_tanaman to it.nama_tanaman }
            is HomeUiState.Loading -> emptyList() // Sementara loading, tidak ada data yang ditampilkan
            is HomeUiState.Error -> emptyList() // Jika terjadi error, kembalikan list kosong
        }
    }
}

