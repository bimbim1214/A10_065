package com.example.uas_pam.ui.view.catatanpanen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.TanamanList
import com.example.uas_pam.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam.ui.customwidget.DynamicSelectTextField
import com.example.uas_pam.ui.navigation.DestinasiNavigasi
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam.ui.viewmodel.catatanpanen.InsertPanenViewModel
import com.example.uas_pam.ui.viewmodel.catatanpanen.InsertPnnUiEvent
import com.example.uas_pam.ui.viewmodel.catatanpanen.InsertPnnUiState
import kotlinx.coroutines.launch


@Composable
fun FormInputPanen(
    insertPnnUiEvent: InsertPnnUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPnnUiEvent) -> Unit = {},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertPnnUiEvent.id_panen,
            onValueChange = { onValueChange(insertPnnUiEvent.copy(id_panen = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Id Panen") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        DynamicSelectTextField(
            modifier = Modifier,
            selectedValue = insertPnnUiEvent.id_tanaman,
            options = TanamanList.DataNamaTanaman(),
            label = "ID Tanaman",
            onValueChangedEvent = {selectedTnm ->
                onValueChange(insertPnnUiEvent.copy(id_tanaman = selectedTnm))

            }
        )

        OutlinedTextField(
            value = insertPnnUiEvent.tanggal_panen,
            onValueChange = { onValueChange(insertPnnUiEvent.copy(tanggal_panen = it)) },
            label = { Text("Tanggal Panen") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertPnnUiEvent.jumlah_panen,
            onValueChange = { onValueChange(insertPnnUiEvent.copy(jumlah_panen = it)) },
            label = { Text("Jumlah Panen") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertPnnUiEvent.keterangan,
            onValueChange = { onValueChange(insertPnnUiEvent.copy(keterangan = it)) },
            label = { Text("Keterangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = false
        )

        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}