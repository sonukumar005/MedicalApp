package com.example.medicalapp.ui_layer.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalapp.ui_layer.viewModel.AppViewModel
import com.example.medicalapp.user_praf.UserPreferencesManager
import kotlinx.coroutines.delay


@Composable
fun userVerificationProcessScreen(
    navController: NavController,
    viewModel: AppViewModel = hiltViewModel(),
    userPreferencesManager: UserPreferencesManager,
) {
    val userID by userPreferencesManager.userIdSignUp.collectAsState(initial = null)
    val refresh = remember { mutableStateOf(false) }
    val getSpecificUserState = viewModel.getSpecificUserState.collectAsState()


    LaunchedEffect(Unit) {
        delay(1000)
        viewModel.getSpecificUser(userID.toString())
    }

    LaunchedEffect(refresh.value) {
        viewModel.getSpecificUser(userID.toString())
        Log.d("TAG", "userVerificationProcessScreen: ${getSpecificUserState.value}")
    }

    Log.d("userID", "userID: $userID")

val isApproved = getSpecificUserState.value.Data?.body()?.get(0)?.isApproved



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (
          isApproved == 1
        ) {
            Text(
                "Verification Successful",
                style = MaterialTheme.typography.headlineLarge
            )
        } else {
            Text(
                text = "Verification in Process",
                style = MaterialTheme.typography.headlineLarge
            )
            Button(onClick = {refresh.value = !refresh.value}  ) {
                Text(
                    text = "Refresh",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }

}