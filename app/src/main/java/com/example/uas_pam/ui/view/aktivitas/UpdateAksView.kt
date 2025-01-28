package com.example.uas_pam.ui.view.aktivitas

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam.ui.navigation.DestinasiNavigasi
import com.example.uas_pam.ui.view.tanaman.DestinasiUpdate
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam.ui.viewmodel.aktivitas.UpdateViewModelAks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateAktivitas: DestinasiNavigasi {
    override val route = "update Aktivitas"
    override val titleRes = "Update Aktivitas"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreenAktivitas(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateViewModelAks = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateAktivitas.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUP = onBack,
                subtitle = "Selamat Datang"
            )
        }
    ){padding ->
        EntryBodyAks(
            modifier = Modifier.padding(padding),
            insertAksUiState = viewModel.updateAksUiState,
            onAktivitasValueChange = viewModel::updateInsertAksState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateAks()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}