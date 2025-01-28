package com.example.uas_pam.ui.view.aktivitas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.PekerjaList
import com.example.uas_pam.TanamanList
import com.example.uas_pam.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam.ui.customwidget.DynamicSelectTextField
import com.example.uas_pam.ui.navigation.DestinasiNavigasi
import com.example.uas_pam.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam.ui.viewmodel.aktivitas.InsertAksUiEvent
import com.example.uas_pam.ui.viewmodel.aktivitas.InsertAksUiState
import com.example.uas_pam.ui.viewmodel.aktivitas.InsertAksViewModel
import kotlinx.coroutines.launch
import com.example.uas_pam.R


@Composable
fun EntryBodyAks(
    insertAksUiState: InsertAksUiState,
    onAktivitasValueChange: (InsertAksUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputAks(
            insertAksUiEvent = insertAksUiState.insertAksUiEvent,
            onValueChange = onAktivitasValueChange,
            modifier = Modifier.fillMaxWidth(),

        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputAks(
    insertAksUiEvent: InsertAksUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertAksUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    val options = listOf("Penyiraman", "Pemupukan", "Pemangkasan", "Panen")
    var expanded by remember { mutableStateOf(false)}

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertAksUiEvent.id_aktivitas,
            onValueChange = { onValueChange(insertAksUiEvent.copy(id_aktivitas = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Id Aktivitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        DynamicSelectTextField(
            modifier = Modifier,
            selectedValue = insertAksUiEvent.id_tanaman,
            options = TanamanList.DataNamaTanaman(),
            label = "ID Tanaman",
            onValueChangedEvent = {selectedTnm ->
                onValueChange(insertAksUiEvent.copy(id_tanaman = selectedTnm))

            }
        )

        DynamicSelectTextField(
            modifier = Modifier,
            selectedValue = insertAksUiEvent.id_pekerja,
            options = PekerjaList.DataNamaPekerja(),
            label = "ID Pekerja",
            onValueChangedEvent = {selectedPkj ->
                onValueChange(insertAksUiEvent.copy(id_pekerja = selectedPkj))

            }
        )


        OutlinedTextField(
            value = insertAksUiEvent.tanggal_aktivitas,
            onValueChange = { onValueChange(insertAksUiEvent.copy(tanggal_aktivitas = it)) },
            label = { Text("tanggal aktivitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = insertAksUiEvent.deskripsi_aktivitas,
                onValueChange = { onValueChange(insertAksUiEvent.copy(deskripsi_aktivitas = it)) },
                label = { Text("Deskripsi Aktivitas") },
                leadingIcon = { Icon(imageVector = Icons.Default.Done, contentDescription = " ") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                enabled = enabled,
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                placeholder = { Text("Deskripsi Aktivitas") }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { status ->
                    DropdownMenuItem(
                        text = { Text(status) },
                        onClick = {
                            onValueChange(insertAksUiEvent.copy(deskripsi_aktivitas = status))
                            expanded = false
                        }
                    )
                }
            }
        }

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
