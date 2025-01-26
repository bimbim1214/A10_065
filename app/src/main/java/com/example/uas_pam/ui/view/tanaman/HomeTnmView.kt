package com.example.uas_pam.ui.view.tanaman

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.R
import com.example.uas_pam.model.Tanaman
import com.example.uas_pam.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam.ui.navigation.DestinasiNavigasi
import com.example.uas_pam.ui.viewmodel.tanaman.HomeUiState
import com.example.uas_pam.ui.viewmodel.tanaman.HomeViewModel
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEnty: () -> Unit,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onDetailClick: (String) -> Unit = {},
    onEditClick: (String) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeTanaman.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getTnm()
                },
                navigateUP = navigateBack
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
                    contentDescription = "Add Tanaman"
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HomeStatus(
                homeUiState = viewModel.tnmUiState,
                retryAction = { viewModel.getTnm() },
                onDetailClick = onDetailClick,
                onDeleteClick = {
                    viewModel.deleteTnm(it.id_tanaman)
                    viewModel.getTnm()
                },
                onEditClick = onEditClick
            )
        }
    }
}


@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tanaman) -> Unit = {},
    onDetailClick: (String) -> Unit,
    onEditClick: (String) -> Unit
){
    when (homeUiState){
        is HomeUiState.Loading -> onLoadinng(modifier = modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.tanaman.isEmpty()){
                return Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Tidak ada data Tanaman")
                }
            } else {
                TnmLayout(
                    tanaman = homeUiState.tanaman,
                    modifier = modifier
                        .fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_tanaman)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    },
                    onEditClick = {
                        onEditClick(it.id_tanaman)
                    }
                )
            }
        is HomeUiState.Error -> onError(retryAction, modifier = modifier.fillMaxSize())
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
fun TnmLayout(
    tanaman: List<Tanaman>,
    modifier: Modifier = Modifier,
    onDetailClick: (Tanaman) -> Unit,
    onEditClick: (Tanaman) -> Unit,
    onDeleteClick: (Tanaman) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp), // Padding jika diperlukan
        verticalArrangement = Arrangement.spacedBy(12.dp) // Jarak antar elemen
    ) {
        tanaman.forEach { tanaman ->
            TnmCard(
                tanaman = tanaman,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(tanaman) },
                onDeleteClick = { onDeleteClick(tanaman) },
                onEditClick = { onEditClick(tanaman) },
                onDetailClick = { onDetailClick(tanaman) }
            )
        }
    }
}

@Composable
fun TnmCard(
    tanaman: Tanaman,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tanaman) -> Unit = {},
    onEditClick: (Tanaman) -> Unit = {},
    onDetailClick: (Tanaman) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Konfirmasi Hapus") },
            text = { Text(text = "Apakah Anda yakin ingin menghapus ${tanaman.nama_tanaman}?") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onDeleteClick(tanaman) // Lakukan penghapusan
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
        modifier = modifier.clickable { onDetailClick(tanaman) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tanaman.nama_tanaman,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = tanaman.id_tanaman,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = tanaman.periode_tanam,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = tanaman.deskripsi_tanaman,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onEditClick(tanaman) }) {
                    Text(text = "Edit")
                }
                Button(onClick = { showDialog = true }) { // Menampilkan dialog konfirmasi
                    Text(text = "Hapus")
                }
            }
        }
    }
}


