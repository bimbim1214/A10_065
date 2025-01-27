package com.example.uas_pam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uas_pam.KebunAplications
import com.example.uas_pam.ui.viewmodel.aktivitas.DetailAksViewModel
import com.example.uas_pam.ui.viewmodel.aktivitas.HomeAksViewModel
import com.example.uas_pam.ui.viewmodel.aktivitas.InsertAksViewModel
import com.example.uas_pam.ui.viewmodel.aktivitas.UpdateViewModelAks
import com.example.uas_pam.ui.viewmodel.catatanpanen.DetailPanenViewModel
import com.example.uas_pam.ui.viewmodel.catatanpanen.HomePanenViewModel
import com.example.uas_pam.ui.viewmodel.catatanpanen.InsertPanenViewModel
import com.example.uas_pam.ui.viewmodel.catatanpanen.UpdatePanenViewModel
import com.example.uas_pam.ui.viewmodel.pekerja.DetailPKJViewModel
import com.example.uas_pam.ui.viewmodel.pekerja.HomePKJViewModel
import com.example.uas_pam.ui.viewmodel.pekerja.InsertPKJViewModel
import com.example.uas_pam.ui.viewmodel.pekerja.UpdateViewModelPekerja
import com.example.uas_pam.ui.viewmodel.tanaman.DetailViewModel
import com.example.uas_pam.ui.viewmodel.tanaman.HomeViewModel
import com.example.uas_pam.ui.viewmodel.tanaman.InsertViewModel
import com.example.uas_pam.ui.viewmodel.tanaman.UpdateTnmViewModel


fun CreationExtras.aplikasiKebun(): KebunAplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KebunAplications)

