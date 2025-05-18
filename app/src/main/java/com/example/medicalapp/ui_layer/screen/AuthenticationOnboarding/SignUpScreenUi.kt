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
import com.example.medicalapp.ui_layer.Navigation.userVerificationProcessUI

import com.example.medicalapp.ui_layer.common.MulticolorText
import com.example.medicalapp.ui_layer.viewModel.AppViewModel


@Composable
fun SignUpScreenUi(
    navController: NavController,
    viewModel: AppViewModel = hiltViewModel(),
) {
    val context = LocalContext.current





    var userName = remember { mutableStateOf("") }
    var userPassword = remember { mutableStateOf("") }
    var userEmail = remember { mutableStateOf("") }
    var userPhone = remember { mutableStateOf("") }
    var userAddress = remember { mutableStateOf("") }
    var userPinCode = remember { mutableStateOf("") }

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
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                placeholder = { Text("Enter your Name") }
            )
            Spacer(modifier = Modifier.size(40.dp))
            OutlinedTextField(
                value = userEmail.value,
                onValueChange = {
                    userEmail.value = it
                },
                placeholder = { Text("Enter Your Email") }
            )
            Spacer(modifier = Modifier.size(30.dp))
            OutlinedTextField(
                value = userPhone.value,
                onValueChange = {
                    userPhone.value = it
                },
                placeholder = { Text("Enter Your phone-number") }
            )
            Spacer(modifier = Modifier.size(30.dp))
            OutlinedTextField(
                value = userPassword.value,
                onValueChange = {
                    userPassword.value = it
                },
                placeholder = { Text("Enter Your Password") }
            )
            Spacer(modifier = Modifier.size(30.dp))
            OutlinedTextField(
                value = userPinCode.value,
                onValueChange = {
                    userPinCode.value = it
                },
                placeholder = { Text("Enter Your PinCode") }
            )
            Spacer(modifier = Modifier.size(30.dp))
            OutlinedTextField(
                value = userAddress.value,
                onValueChange = {
                    userAddress.value = it
                },
                placeholder = { Text("Enter Your Address") }
            )
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                if (userName.value.isEmpty() || userEmail.value.isEmpty() || userPhone.value.isEmpty() || userPassword.value.isEmpty() || userAddress.value.isEmpty() || userPinCode.value.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Please fill all the fields",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.signUpUser(
                        name = userName.value,
                        email = userEmail.value,
                        password = userPassword.value,
                        phone = userPhone.value,
                        address = userAddress.value,
                        pincode = userPinCode.value
                    )
                    navController.navigate(userVerificationProcessUI)
                }

            }) {
                Text("Submit")
            }
            Spacer(modifier = Modifier.size(20.dp))
            MulticolorText("Already have an account?", "Login", modifier = Modifier.clickable {
                navController.popBackStack()
            })
        }


    }

}