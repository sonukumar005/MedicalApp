package com.example.medicalapp.ui_layer.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


import androidx.navigation.compose.rememberNavController
import com.example.medicalapp.ui_layer.screen.CustomerFacingScreens.HomeScreen
import com.example.medicalapp.ui_layer.screen.AuthenticationOnboarding.SignInScreenUI
import com.example.medicalapp.ui_layer.screen.AuthenticationOnboarding.SignUpScreenUi
import com.example.medicalapp.ui_layer.screen.AuthenticationOnboarding.userRejectionScreen
import com.example.medicalapp.ui_layer.screen.AuthenticationOnboarding.userVerificationProcessScreen
import com.example.medicalapp.ui_layer.screen.AuthenticationOnboarding.userVerifiedScreen
import com.example.medicalapp.ui_layer.viewModel.AppViewModel
import com.example.medicalapp.user_praf.UserPreferencesManager
import kotlinx.coroutines.delay


//import com.example.medicalapp.user_praf.UserPreferencesManager

@Composable
fun ApkNavigation(
   viewModel: AppViewModel = hiltViewModel(),
   userPreferencesManager: UserPreferencesManager
) {
    val userIDLogin by userPreferencesManager.userIdLogin.collectAsState(initial = null)
    val userIDSignUp by userPreferencesManager.userIdSignUp.collectAsState(initial = null)
    val navController = rememberNavController()










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
                    delay(1000)

                when {
                    !userIDSignUp.isNullOrEmpty() -> {
                        navController.navigate(userVerificationProcessUI)
                    }
                    !userIDLogin.isNullOrEmpty() -> {
                        navController.navigate(HomeUI)
                    }
                    else -> {
                        navController.navigate(LoginUI)

                    }
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
            userVerificationProcessScreen(navController = navController,
                userPreferencesManager = userPreferencesManager
            )

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