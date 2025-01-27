package com.example.uas_pam.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uas_pam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostumeTopAppBar(
    title: String,
    subtitle: String, // Menambahkan parameter subtitle
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUP: () -> Unit = {},
    onRefresh: () -> Unit = {},
) {
    val backgroundColor = Color(0xFF81C784) // Warna hijau pastel seperti kebun
    val contentColor = Color(0xFF1B5E20) // Warna hijau lebih tua untuk kontras

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Navbar seperti pada HomeNavbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF81C784), RoundedCornerShape(bottomEnd = 50.dp))
                .padding(start = 15.dp, bottom = 38.dp)
        ) {
            // TopAppBar for the title and menu item
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // Judul utama
                        Text(
                            title,
                            color = contentColor,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Serif, // Gaya font untuk kesan alam
                                fontWeight = FontWeight.Bold, // Menambahkan ketebalan pada judul
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.padding(top = 22.dp)
                        )
                        // Subjudul di bawah judul utama
                        Text(
                            subtitle, // Teks tambahan di bawah judul
                            color = Color(0xFF388E3C), // Warna lebih terang untuk kontras
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp // Ukuran font lebih kecil
                            ),
                            modifier = Modifier.padding(top = 1.dp) // Menambahkan jarak antar judul dan subjudul
                        )
                    }
                },
                actions = {},
                modifier = modifier.background(backgroundColor),
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    if (canNavigateBack) {
                        IconButton(onClick = navigateUP) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Back",
                                tint = contentColor
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = backgroundColor,
                    scrolledContainerColor = backgroundColor,
                    navigationIconContentColor = contentColor,
                    titleContentColor = contentColor,
                    actionIconContentColor = contentColor
                )
            )
        }

        // Tombol Refresh di kiri bawah
        IconButton(
            onClick = onRefresh,
            modifier = Modifier
                .align(Alignment.BottomStart) // Menempatkan di kiri bawah
                .padding(start = 20.dp, bottom = 1.dp) // Menambah jarak bottom agar lebih jauh dari navbar
        ) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = "Refresh",
                tint = contentColor
            )
        }
        Image(
            painter = painterResource(id = R.drawable.kebun),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .padding(13.dp)
                .size(60.dp)
                .clip(RoundedCornerShape(50.dp)) // Bentuk gambar bundar
                .shadow(8.dp, RoundedCornerShape(50.dp)) // Bayangan halus yang lebih besar
                .border(2.dp, color = androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(50.dp)) // Border putih tipis
                .align(Alignment.TopEnd)
        )
    }
}





