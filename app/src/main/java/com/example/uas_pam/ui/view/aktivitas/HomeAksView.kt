package com.example.uas_pam.ui.view.aktivitas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.R
import com.example.uas_pam.model.AktivitasPertanian
import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam.ui.navigation.DestinasiNavigasi
import com.example.uas_pam.ui.view.tanaman.DestinasiHomeTanaman
import com.example.uas_pam.ui.view.tanaman.HomeStatus
import com.example.uas_pam.ui.view.tanaman.TnmCard
import com.example.uas_pam.ui.view.tanaman.TnmLayout
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam.ui.viewmodel.aktivitas.HomeAksUiState
import com.example.uas_pam.ui.viewmodel.aktivitas.HomeAksViewModel
import com.example.uas_pam.ui.viewmodel.tanaman.HomeUiState
import com.example.uas_pam.ui.viewmodel.tanaman.HomeViewModel


@Composable
fun AksLayout(
    aktivitasPertanian: List<AktivitasPertanian>,
    modifier: Modifier = Modifier,
    onDetailClick: (AktivitasPertanian) -> Unit,
    onEditClick: (AktivitasPertanian) -> Unit,
    onDeleteClick: (AktivitasPertanian) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp), // Padding jika diperlukan
        verticalArrangement = Arrangement.spacedBy(12.dp) // Jarak antar elemen
    ) {
        aktivitasPertanian.forEach { aktivitasPertanian ->
            AksCard(
                aktivitasPertanian = aktivitasPertanian,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(aktivitasPertanian) },
                onDeleteClick = { onDeleteClick(aktivitasPertanian) },
                onEditClick = { onEditClick(aktivitasPertanian) },
                onDetailClick = { onDetailClick(aktivitasPertanian) }
            )
        }
    }
}


@Composable
fun AksCard(
    aktivitasPertanian: AktivitasPertanian,
    modifier: Modifier = Modifier,
    onDeleteClick: (AktivitasPertanian) -> Unit = {},
    onEditClick: (AktivitasPertanian) -> Unit = {},
    onDetailClick: (AktivitasPertanian) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Konfirmasi Hapus") },
            text = { Text(text = "Apakah Anda yakin ingin menghapus aktivitas dengan ID ${aktivitasPertanian.id_aktivitas}?") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onDeleteClick(aktivitasPertanian) // Lakukan penghapusan
                }) {
                    Text("Hapus")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    Card(
        modifier = modifier
            .clickable { onDetailClick(aktivitasPertanian) }
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)) // Warna hijau pastel untuk latar
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = aktivitasPertanian.id_aktivitas,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.weight(3f)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = aktivitasPertanian.id_tanaman,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Text(
                text = "ID Pekerja: ${aktivitasPertanian.id_pekerja}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF1B5E20),
                    fontWeight = FontWeight.Normal
                )
            )
            Text(
                text = aktivitasPertanian.tanggal_aktivitas,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF388E3C),
                    fontWeight = FontWeight.Normal
                )
            )
            Text(
                text = aktivitasPertanian.deskripsi_aktivitas,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Normal
                )
            )
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onEditClick(aktivitasPertanian) },
                    modifier = Modifier.width(90.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784)),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "Edit", color = Color.White)
                }
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.width(90.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "Hapus", color = Color.White)
                }
            }
        }
    }
}
