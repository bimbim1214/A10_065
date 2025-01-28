package com.example.uas_pam.ui.view.aktivitas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.PekerjaList
import com.example.uas_pam.TanamanList
import com.example.uas_pam.ui.navigation.DestinasiNavigasi
import com.example.uas_pam.ui.view.tanaman.DestinasiDetail
import com.example.uas_pam.ui.view.tanaman.DetailRow
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam.ui.viewmodel.aktivitas.DetailAksViewModel
import com.example.uas_pam.ui.viewmodel.tanaman.DetailViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenAktivitas(
    idAktivitas: String,
    onEditClick: (String) -> Unit = { },
    onDeleteClick: (String) -> Unit = { },
    onBackClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: DetailAksViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val aktivitasPertanian = viewModel.DetailaksuiState.detailAksEvent

    val tanamanList = TanamanList.DataNamaTanaman()
    val pekerjaList = PekerjaList.DataNamaPekerja()

    val namatanaman = tanamanList.find { it.first == aktivitasPertanian.id_tanaman }?.second ?: "tanaman tidak diketahui"
    val namapekerja = pekerjaList.find { it.first == aktivitasPertanian.id_pekerja }?.second ?: "pekerja tidak diketahui"

    LaunchedEffect(idAktivitas) {
        viewModel.fetchDetailAktivitas(idAktivitas)
    }

    val isLoading = viewModel.DetailaksuiState.isLoading
    val isError = viewModel.DetailaksuiState.isError
    val errorMessage = viewModel.DetailaksuiState.errorMessage

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(DestinasiDetailAktivitas.titleRes) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(aktivitasPertanian.id_aktivitas) },
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Data")
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                else if (isError) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else if (viewModel.DetailaksuiState.isUiEventNotEmpty) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // Use Row for each detail with label and value aligned
                                DetailRowAks(label = "ID Aktivitas", value = aktivitasPertanian.id_aktivitas)
                                DetailRowAks(label = "ID Tanaman", value = aktivitasPertanian.id_tanaman)
                                DetailRowAks(label = "Nama Tanaman", value = namatanaman)
                                DetailRowAks(label = "ID Pekerja", value = aktivitasPertanian.id_pekerja)
                                DetailRowAks(label = "Nama Pekerja", value = namapekerja)
                                DetailRowAks(label = "Tanggal Aktivitas", value = aktivitasPertanian.tanggal_aktivitas)
                                DetailRowAks(label = "Deskripsi Aktivitas", value = aktivitasPertanian.deskripsi_aktivitas)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun DetailRowAks(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ": $value",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(2f)
        )
    }
}