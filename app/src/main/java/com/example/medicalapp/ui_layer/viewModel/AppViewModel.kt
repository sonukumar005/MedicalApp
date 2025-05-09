package com.example.medicalapp.ui_layer.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.medicalapp.Data.Repo.repo
import com.example.medicalapp.Data.responce.GetAllUserResponce
import com.example.medicalapp.Data.responce.LoginUserResponce
import com.example.medicalapp.Data.responce.getSpecificUserResponce

import com.example.medicalapp.Data.responce.signUpResponce
import com.example.medicalapp.State
import com.example.medicalapp.user_praf.UserPreferencesManager

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import retrofit2.Response


@HiltViewModel
class AppViewModel @Inject constructor(
    private val repo: repo,
    private val userPreferencesManager: UserPreferencesManager
) : ViewModel() {

    private val _signUpState: MutableStateFlow<signUpState> = MutableStateFlow(signUpState())
    val signUpState = _signUpState.asStateFlow()
    private val _loginState: MutableStateFlow<loginState> = MutableStateFlow(loginState())
    val loginState = _loginState.asStateFlow()

    private val _getSpecificUserSate: MutableStateFlow<getSpecificUserState> =
        MutableStateFlow(getSpecificUserState())
    val getSpecificUserState = _getSpecificUserSate.asStateFlow()


    fun signUpUser(
        name: String,
        email: String,
        password: String,
        phone: String,
        address: String,
        pincode: String
    ) {
        viewModelScope.launch {
            repo.signUpUser(
                name = name,
                email = email,
                password = password,
                phone = phone,
                address = address,
                pincode = pincode
            ).collect { it ->
                when (it) {
                    is State.Loading -> {
                        _signUpState.value = signUpState(Loading = true)
                    }

                    is State.Success -> {
                        val userID = it.data?.body()?.message
                        userPreferencesManager.saveUserIdSignUp(userID.toString())


                        _signUpState.value = signUpState(Data = it.data, Loading = false)

                    }

                    is State.Error -> {
                        _signUpState.value = signUpState(error = it.message, Loading = false)
                    }
                }
            }
        }
    }

    fun loginUser(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            repo.loginUser(
                email = email,
                password = password
            ).collect { state ->
                when (state) {
                    is com.example.medicalapp.State.Loading -> {
                        _loginState.value = loginState(Loading = true)
                    }

                    is State.Success -> {
                        val userIDLogin = state.data?.body()?.message
                        userPreferencesManager.saveUserIdLogin(userIDLogin.toString())

                        _loginState.value = loginState(Data = state.data, Loading = false)
                    }

                    is State.Error -> {
                        _loginState.value = loginState(error = state.message, Loading = false)
                    }
                }
            }
        }
    }

    fun getSpecificUser(
        userId: String
    ) {
        viewModelScope.launch {
            repo.getSpecificUser(
                userId = userId
            ).collect { it ->
                when (it) {
                    is com.example.medicalapp.State.Loading -> {
                        _getSpecificUserSate.value = getSpecificUserState(Loading = true)
                    }

                    is com.example.medicalapp.State.Success -> {
                        _getSpecificUserSate.value =
                            getSpecificUserState(data = it.data, Loading = false)
                    }

                    is com.example.medicalapp.State.Error -> {
                        _getSpecificUserSate.value =
                            getSpecificUserState(error = it.message, Loading = false)
                    }
                }
            }
        }
    }

}


data class signUpState(
    val Loading: Boolean = false,
    val Data: Response<signUpResponce>? = null,
    val error: String? = null
)

data class loginState(
    val Loading: Boolean = false,
    val Data: Response<LoginUserResponce>? = null,
    val error: String? = null
)

data class getSpecificUserState(
    val Loading: Boolean = false,
    val data: Response<getSpecificUserResponce>? = null,
    val error: String? = null
)