package com.example.medicalapp.ui_layer.Navigation

import android.net.wifi.hotspot2.pps.HomeSp
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.Navigation
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


import androidx.navigation.compose.rememberNavController
import com.example.medicalapp.ui_layer.Navigation.userVerificationProcessUI
import com.example.medicalapp.ui_layer.screen.HomeScreen
import com.example.medicalapp.ui_layer.screen.SignInScreenUI
import com.example.medicalapp.ui_layer.screen.SignUpScreenUi
import com.example.medicalapp.ui_layer.screen.userRejectionScreen
import com.example.medicalapp.ui_layer.screen.userVerificationProcessScreen
import com.example.medicalapp.ui_layer.screen.userVerifiedScreen
import com.example.medicalapp.ui_layer.viewModel.AppViewModel
import com.example.medicalapp.user_praf.UserPreferencesManager


//import com.example.medicalapp.user_praf.UserPreferencesManager

@Composable
fun ApkNavigation(
   viewModel: AppViewModel = hiltViewModel(),
   userPreferencesManager: UserPreferencesManager
) {
    val navController = rememberNavController()
    val userIDLogin by userPreferencesManager.userIdLogin.collectAsState(initial = null)
    val userIDSignUp by userPreferencesManager.userIdSignUp.collectAsState(initial = null)

    val getSpecificUserState = viewModel.getSpecificUserState.collectAsState()
    val signupState = viewModel.signUpState.collectAsState()
    val userID = signupState.value.Data?.body()?.message
    LaunchedEffect(Unit) {
        if (userID != null) {
            viewModel.getSpecificUser(userID)
        }
    }

    val isApproved = getSpecificUserState.value.data?.body()?.isApproved



//    val userid by userPreferencesManager.userId.collectAsState(initial = null)
//    Log.d("TAG", "ApkNavigation: $userid")
//    LaunchedEffect(userid) {
//        if (userid != null) {
//            navController.navigate(HomeUI)
//        } else {
//            navController.navigate(LoginUI)
//        }
//    }
//    var selected by remember { mutableStateOf(0) }
//
//    val bottomNavItems = listOf(
//        botomNavItem(
//            title = "Home",
//            icon = Icons.Default.Home
//        ),
//        botomNavItem(
//            title = "Profile",
//            icon = Icons.Default.Add
//        ),
//        botomNavItem(
//            title = "Settings",
//            icon = Icons.Default.Build
//        )
//    )
//    Box {
//        Scaffold(
//            bottomBar = {
//                NavigationBar() {
//                    bottomNavItems.forEachIndexed { index, item ->
//                        NavigationBarItem(
//                            icon = { Icon(item.icon, contentDescription = null) },
//                            label = {Text(item.title) },
//                            selected = selected == index,
//                            onClick = {
//                                selected = index
//                            }
//                        )
//                    }
//                }
//            }
//        ) { innerPadding ->
//            Box(modifier = Modifier.padding(innerPadding)) {
//                when (selected) {
//                    0 -> HomeScreen(navController = navController)
//                    1 -> SignUpScreenUi(navController = navController)
//                    2 -> SignInScreenUI(navController = navController)
//                }
//            }
//        }
//    }






    NavHost(
        navController = navController,
        startDestination = spalashScreen

        ) {
        composable<spalashScreen> {
            LaunchedEffect(Unit) {
                if (userIDSignUp != null) {
                    navController.navigate(userVerificationProcessUI)
                } else if (userIDLogin != null) {
                    navController.navigate(HomeUI)
                } else {
                    navController.navigate(LoginUI)
                }


            }
        }
        composable<SignUpUI> {
            SignUpScreenUi(navController = navController)
        }
        composable<LoginUI> {
            SignInScreenUI(navController = navController)

        }
        composable<HomeUI> {
            HomeScreen(navController = navController)
        }
        composable<userVerificationProcessUI> {
            userVerificationProcessScreen(navController = navController)

        }
        composable<userVerifiedUI> {
            userVerifiedScreen(navController = navController)
        }
        composable<RejectionUI> {
            userRejectionScreen(navController = navController)
        }


    }
}



//data class botomNavItem(
//    val title: String,
//    val icon: ImageVector
//)