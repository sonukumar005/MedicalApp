package com.example.medicalapp

import dagger.hilt.android.AndroidEntryPoint


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medicalapp.ui.theme.MedicalAppTheme
import com.example.medicalapp.ui_layer.Navigation.ApkNavigation
import com.example.medicalapp.user_praf.UserPreferencesManager


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val userPreferencesManager = UserPreferencesManager(this)
        setContent {
            MedicalAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ApkNavigation(
                        userPreferencesManager = userPreferencesManager
                    )
                }
            }
        }
    }
}

