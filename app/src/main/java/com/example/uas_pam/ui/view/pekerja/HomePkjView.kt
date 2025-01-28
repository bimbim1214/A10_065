package com.example.uas_pam.ui.view.pekerja

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.R
import com.example.uas_pam.model.Pekerja
import com.example.uas_pam.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam.ui.navigation.DestinasiNavigasi
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam.ui.viewmodel.pekerja.HomePKJUiState
import com.example.uas_pam.ui.viewmodel.pekerja.HomePKJViewModel

object DestinasiHomePekerja : DestinasiNavigasi {
    override val route = "home_Pekerja"
    override val titleRes = "Home Pekerja"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePKJScreen(
    navigateToItemEnty: () -> Unit,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onDetailClick: (String) -> Unit = {},
    onEditClick: (String) -> Unit,
    viewModel: HomePKJViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomePekerja.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPkj()
                },
                navigateUP = navigateBack,
                subtitle = "Selamat Datang"
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEnty,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Pekerja"
                )
            }
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            HomePKJStatus(
                homePKJUiState = viewModel.pkjUiState,
                retryAction = { viewModel.getPkj() },
                onDetailClick = onDetailClick,
                onDeleteClick = {
                    viewModel.deletePkj(it.id_pekerja)
                    viewModel.getPkj()
                },
                onEditClick = onEditClick
            )
        }

    }
}


@Composable
fun HomePKJStatus(
    homePKJUiState: HomePKJUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pekerja) -> Unit = {},
    onDetailClick: (String) -> Unit,
    onEditClick: (String) -> Unit
){
    when (homePKJUiState){
        is HomePKJUiState.Loading -> onLoadinng(modifier = modifier.fillMaxSize())

        is HomePKJUiState.Success ->
            if (homePKJUiState.pekerja.isEmpty()){
                return Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Tidak ada data Pekerja")
                }
            } else {
                PkjLayout(
                    pekerja = homePKJUiState.pekerja,
                    modifier = modifier.fillMaxSize(),
                    onDetailClick = {
                        onDetailClick(it.id_pekerja)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    },
                    onEditClick = {
                        onEditClick(it.id_pekerja)
                    }
                )
            }
        is  HomePKJUiState.Error -> onError(retryAction, modifier = modifier.fillMaxSize())
    }
}



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
fun PkjLayout(
    pekerja:List<Pekerja>,
    modifier: Modifier = Modifier,
    onDetailClick: (Pekerja) -> Unit,
    onEditClick: (Pekerja) -> Unit,
    onDeleteClick: (Pekerja) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp), // Padding jika diperlukan
        verticalArrangement = Arrangement.spacedBy(12.dp) // Jarak antar elemen
    ) {
        pekerja.forEach { pekerja ->
            PkjCard(
                pekerja = pekerja,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pekerja) },
                onDeleteClick = { onDeleteClick(pekerja) },
                onEditClick = { onEditClick(pekerja) },
                onDetailClick = { onDetailClick(pekerja) }
            )
        }
    }
}

@Composable
fun PkjCard(
    pekerja: Pekerja,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pekerja) -> Unit = {},
    onEditClick: (Pekerja) -> Unit = {},
    onDetailClick: (Pekerja) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Konfirmasi Hapus") },
            text = { Text(text = "Apakah Anda yakin ingin menghapus ${pekerja.nama_pekerja}?") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onDeleteClick(pekerja) // Lakukan penghapusan
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
            .clickable { onDetailClick(pekerja) }
            .padding(8.dp), // Memberikan padding luar untuk kartu
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp), // Menambahkan bayangan lembut
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)) // Warna latar belakang pastel
    ) {
        Column(
            modifier = Modifier.padding(10.dp), // Padding lebih besar untuk kenyamanan
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pekerja.nama_pekerja,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold, // Judul tebal
                        color = Color.Black // Warna hitam untuk judul
                    ),
                    modifier = Modifier.weight(3f)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = pekerja.id_pekerja,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal, // Menambahkan sedikit variasi pada ketebalan font
                        color = Color(0xFF4CAF50) // Warna hijau untuk ID
                    )
                )
            }
            Text(
                text = pekerja.jabatan,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF1B5E20), // Warna hijau lebih tua untuk jabatan
                    fontWeight = FontWeight.Normal, // Agar lebih ringan
                    fontSize = 18.sp
                )
            )
            Text(
                text = pekerja.kontak_pekerja,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp // Ukuran font lebih kecil untuk kontak
                )
            )
            Spacer(Modifier.height(12.dp)) // Menambahkan jarak antar elemen

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Tombol Edit
                Button(
                    onClick = { onEditClick(pekerja) },
                    modifier = Modifier.width(90.dp), // Lebar tombol disesuaikan
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784)), // Warna tombol yang lebih soft
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "Edit", color = Color.White)
                }
                // Tombol Hapus
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

