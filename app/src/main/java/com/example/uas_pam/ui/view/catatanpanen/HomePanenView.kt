package com.example.uas_pam.ui.view.catatanpanen

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
import com.example.uas_pam.model.CatatanPanen
import com.example.uas_pam.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam.ui.navigation.DestinasiNavigasi
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam.ui.viewmodel.catatanpanen.HomePanenUiState
import com.example.uas_pam.ui.viewmodel.catatanpanen.HomePanenViewModel


@Composable
fun onLoadinng(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(100.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun onError(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = "Error Image",
            modifier = Modifier
                .size(100.dp) //atur ukuran gambar menajdi 100x100
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PnnLayout(
    catatanPanen: List<CatatanPanen>,
    modifier: Modifier = Modifier,
    onDetailClick: (CatatanPanen) -> Unit,
    onEditClick: (CatatanPanen) -> Unit,
    onDeleteClick: (CatatanPanen) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp), // Padding jika diperlukan
        verticalArrangement = Arrangement.spacedBy(12.dp) // Jarak antar elemen
    ) {
        catatanPanen.forEach { catatanPanen ->
            PnnCard(
                catatanPanen = catatanPanen,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(catatanPanen) },
                onDeleteClick = { onDeleteClick(catatanPanen) },
                onEditClick = { onEditClick(catatanPanen) },
                onDetailClick = { onDetailClick(catatanPanen) }
            )
        }
    }
}

@Composable
fun PnnCard(
    catatanPanen: CatatanPanen,
    modifier: Modifier = Modifier,
    onDeleteClick: (CatatanPanen) -> Unit = {},
    onEditClick: (CatatanPanen) -> Unit = {},
    onDetailClick: (CatatanPanen) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Konfirmasi Hapus") },
            text = { Text(text = "Apakah Anda yakin ingin menghapus catatan panen dengan ID ${catatanPanen.id_panen}?") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onDeleteClick(catatanPanen) // Lakukan penghapusan
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
            .clickable { onDetailClick(catatanPanen) }
            .padding(8.dp), // Memberikan padding luar untuk kartu
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp), // Menambahkan bayangan lembut
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)) // Warna latar belakang pastel
    ) {
        Column(
            modifier = Modifier.padding(10.dp), // Padding lebih besar untuk kenyamanan
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = catatanPanen.id_panen,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold, // Judul tebal
                        color = Color.Black // Warna hitam untuk judul
                    ),
                    modifier = Modifier.weight(3f)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = catatanPanen.id_tanaman,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF4CAF50) // Warna hijau untuk ID tanaman
                    )
                )
            }
            Text(
                text = catatanPanen.tanggal_panen,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF1B5E20), // Warna hijau lebih tua untuk deskripsi
                    fontWeight = FontWeight.Normal
                )
            )
            Text(
                text = catatanPanen.jumlah_panen,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF388E3C),
                    fontWeight = FontWeight.Normal
                )
            )
            Text(
                text = catatanPanen.keterangan,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Normal
                )
            )
            //Spacer(Modifier.height(1.dp)) // Menambahkan jarak antar elemen

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onEditClick(catatanPanen) },
                    modifier = Modifier.width(90.dp), // Lebar tombol disesuaikan
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784)), // Warna tombol yang lebih soft
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "Edit", color = Color.White)
                }
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.width(90.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)), // Warna merah untuk tombol Hapus
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "Hapus", color = Color.White)
                }
            }
        }
    }
}
