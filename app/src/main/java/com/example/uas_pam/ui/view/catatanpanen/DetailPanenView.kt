package com.example.uas_pam.ui.view.catatanpanen

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
import com.example.uas_pam.ui.navigation.DestinasiNavigasi
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam.ui.viewmodel.catatanpanen.DetailPanenViewModel

object DestinasiDetailPanen : DestinasiNavigasi {
    override val route = "detail Panen"
    override val titleRes = "Detail Panen"
    const val ID = "id"
    val routeWithArgs = "$route/{$ID}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenPanen(
    idPanen: String,
    onEditClick: (String) -> Unit = { },
    onDeleteClick: (String) -> Unit = { },
    onBackClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: DetailPanenViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val catatanPanen = viewModel.DetailPnnUiState.detailPnnEvent

    LaunchedEffect(idPanen) {
        viewModel.fetchDetailPanen(idPanen)
    }

    val isLoading = viewModel.DetailPnnUiState.isLoading
    val isError = viewModel.DetailPnnUiState.isError
    val errorMessage = viewModel.DetailPnnUiState.errorMessage

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(DestinasiDetailPanen.titleRes) },
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
                onClick = { onEditClick(catatanPanen.id_panen) },
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
                else if (viewModel.DetailPnnUiState.isUiEventNotEmpty) {
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
                                DetailRowPnn(label = "ID Panen", value = catatanPanen.id_panen)
                                DetailRowPnn(label = "ID Tanaman", value = catatanPanen.id_tanaman)
                                DetailRowPnn(label = "Tanggal Panen", value = catatanPanen.tanggal_panen)
                                DetailRowPnn(label = "Jumlah Panen", value = catatanPanen.jumlah_panen)
                                DetailRowPnn(label = "Keterangan", value = catatanPanen.keterangan)
                            }
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun DetailRowPnn(label: String, value: String) {
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