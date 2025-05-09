package com.example.medicalapp.ui_layer.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.medicalapp.ui_layer.Navigation.LoginUI

@Composable
fun userVerifiedScreen(
    navController: NavController,
) {
Text(
    text = "userVerifiedScreen",
)
    TextButton(
        onClick = {
            navController.navigate(LoginUI)
        }
    ) {
        Text(text = "Go to Login screen")
    }
}