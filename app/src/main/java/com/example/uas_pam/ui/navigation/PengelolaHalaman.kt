package com.example.uas_pam.ui.navigation

import DestinasiHomeAwal
import HomeScreenAwal
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uas_pam.ui.view.aktivitas.DestinasiDetailAktivitas
import com.example.uas_pam.ui.view.aktivitas.DestinasiEntryAktivitas
import com.example.uas_pam.ui.view.aktivitas.DestinasiHomeAktivitas
import com.example.uas_pam.ui.view.aktivitas.DestinasiUpdateAktivitas
import com.example.uas_pam.ui.view.aktivitas.DetailScreenAktivitas
import com.example.uas_pam.ui.view.aktivitas.EntryAksScreen
import com.example.uas_pam.ui.view.aktivitas.HomeViewAks
import com.example.uas_pam.ui.view.aktivitas.UpdateScreenAktivitas
import com.example.uas_pam.ui.view.catatanpanen.DestinasiDetailPanen
import com.example.uas_pam.ui.view.catatanpanen.DestinasiEntryPanen
import com.example.uas_pam.ui.view.catatanpanen.DestinasiHomePanen
import com.example.uas_pam.ui.view.catatanpanen.DestinasiUpdatePanen
import com.example.uas_pam.ui.view.catatanpanen.DetailScreenPanen
import com.example.uas_pam.ui.view.catatanpanen.EntryPnnScreen
import com.example.uas_pam.ui.view.catatanpanen.HomeViewPnn
import com.example.uas_pam.ui.view.catatanpanen.UpdateScreenPanen
import com.example.uas_pam.ui.view.pekerja.DestinasiDetailPekerja
import com.example.uas_pam.ui.view.pekerja.DestinasiEntryPekerja
import com.example.uas_pam.ui.view.pekerja.DestinasiHomePekerja
import com.example.uas_pam.ui.view.pekerja.DestinasiUpdatePekerja
import com.example.uas_pam.ui.view.pekerja.DetailScreenPekerja
import com.example.uas_pam.ui.view.pekerja.EntryPkjScreen
import com.example.uas_pam.ui.view.pekerja.HomePKJScreen
import com.example.uas_pam.ui.view.pekerja.UpdateScreenPekerja
import com.example.uas_pam.ui.view.tanaman.DestinasiDetail
import com.example.uas_pam.ui.view.tanaman.DestinasiEntry
import com.example.uas_pam.ui.view.tanaman.DestinasiHomeTanaman
import com.example.uas_pam.ui.view.tanaman.DestinasiUpdate
import com.example.uas_pam.ui.view.tanaman.DetailScreen
import com.example.uas_pam.ui.view.tanaman.EntryTnmScreen
import com.example.uas_pam.ui.view.tanaman.HomeScreen
import com.example.uas_pam.ui.view.tanaman.UpdateScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeTanaman.route,
        modifier = Modifier,
    ) {
//        composable(
//            route = DestinasiHomeAwal.route
//        ) {
//            HomeScreenAwal(
//                onTanamanClick = {navController.navigate(DestinasiHomeTanaman.route) },
//                onPekerjaClick = {navController.navigate(DestinasiHomePekerja.route)},
//                onAktivitasClick = {navController.navigate(DestinasiHomeAktivitas.route)},
//                onPanenClick = {navController.navigate(DestinasiHomePanen.route)}
//
//            )
//        }

        composable(DestinasiHomeTanaman.route) {
            HomeScreen(
                navigateToItemEnty = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = {
                    navController.navigate("${DestinasiDetail.route}/$it")
                },
                onEditClick = {navController.navigate("${DestinasiUpdate.route}/$it")},
                navigateBack = {navController.navigate(DestinasiHomeAwal.route)},
                onTanamanClick = {navController.navigate(DestinasiHomeTanaman.route) },
                onPekerjaClick = {navController.navigate(DestinasiHomePekerja.route)},
                onAktivitasClick = {navController.navigate(DestinasiHomeAktivitas.route)},
                onPanenClick = {navController.navigate(DestinasiHomePanen.route)}
            )
        }
        composable(DestinasiEntry.route) {
            EntryTnmScreen(navigateBack = {
                navController.navigate(DestinasiHomeTanaman.route) {
                    popUpTo(DestinasiHomeTanaman.route)
                    {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiDetail.ID)
            id?.let { nim ->
                DetailScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
//                    onEditClick = {
//                        navController.navigate("${DestinasiUpdate.route}/$id")
//                    },
                    id = id,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            route = DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.ID) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(DestinasiUpdate.ID)
            id?.let {
                UpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeTanaman.route) {
                            popUpTo(DestinasiHomeTanaman.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        //Pengelola Halaman Pekerja
        composable(
            route = DestinasiHomePekerja.route
        ) {
            HomePKJScreen(
                navigateToItemEnty = {navController.navigate(DestinasiEntryPekerja.route)},
                onDetailClick = {
                    navController.navigate("${DestinasiDetailPekerja.route}/$it")
 //                   println("PengelolaHalaman: id = $id")
                },
                onEditClick = {navController.navigate("${DestinasiUpdatePekerja.route}/$it")},
                modifier = Modifier,
                navigateBack = {navController.popBackStack()},
            )
        }
        composable(DestinasiEntryPekerja.route) {
            EntryPkjScreen(navigateBack = {
                navController.navigate(DestinasiHomePekerja.route) {
                    popUpTo(DestinasiHomePekerja.route)
                    {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailPekerja.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailPekerja.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiDetailPekerja.ID)
            id?.let { nim ->
                DetailScreenPekerja(
                    onBackClick = {
                        navController.popBackStack()
                    },
//                    onEditClick = {
//                        navController.navigate("${DestinasiUpdate.route}/$id")
//                    },
                    idpekerja = id,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            route = DestinasiUpdatePekerja.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdatePekerja.ID) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(DestinasiUpdatePekerja.ID)
            id?.let {
                UpdateScreenPekerja(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomePekerja.route) {
                            popUpTo(DestinasiHomePekerja.route) { inclusive = true }
                        }
                    }
                )
            }
        }




        //Home Panen
        composable(DestinasiHomePanen.route) {
            HomeViewPnn(
                navigateToItemEnty = { navController.navigate(DestinasiEntryPanen.route) },
                onDetailClick = {
                    navController.navigate("${DestinasiDetailPanen.route}/$it")
                },
                onEditClick = {navController.navigate("${DestinasiUpdatePanen.route}/$it")},
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(DestinasiEntryPanen.route) {
            EntryPnnScreen(navigateBack = {
                navController.navigate(DestinasiHomePanen.route) {
                    popUpTo(DestinasiHomePanen.route)
                    {
                        inclusive = true
                    }
                }
            })
        }

    }
}