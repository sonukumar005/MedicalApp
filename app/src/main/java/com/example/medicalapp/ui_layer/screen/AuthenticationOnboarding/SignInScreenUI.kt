package com.example.medicalapp.ui_layer.screen.AuthenticationOnboarding

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.medicalapp.R
import com.example.medicalapp.ui_layer.Navigation.HomeUI
import com.example.medicalapp.ui_layer.Navigation.SignUpUI
import com.example.medicalapp.ui_layer.common.MulticolorText
import com.example.medicalapp.ui_layer.viewModel.AppViewModel


@Composable
fun SignInScreenUI(
    viewModel: AppViewModel = hiltViewModel(),
    navController: NavController,
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val loginState = viewModel.loginState.collectAsState()
    val loginData = loginState.value.Data
    LaunchedEffect(loginData) {
        if (loginData != null) {
            if (loginData.isSuccessful) {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                navController.navigate(HomeUI)
            }
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
            )
            Spacer(modifier = Modifier.size(40.dp))
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                placeholder = { Text("Enter Your Email") }
            )
            Spacer(modifier = Modifier.size(40.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                placeholder = { Text("Enter Your Password") }
            )
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                viewModel.loginUser(
                    email = email.value,
                    password = password.value
                )
            }) {
                Text("Login User")
            }
            Spacer(modifier = Modifier.size(40.dp))
            MulticolorText("Don't have an account?", "signup", modifier = Modifier.clickable {
                navController.navigate(SignUpUI)
            })
        }
    }
}