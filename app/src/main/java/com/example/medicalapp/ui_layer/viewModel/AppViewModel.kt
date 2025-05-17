package com.example.medicalapp.ui_layer.viewModel


import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.medicalapp.Data.Repo.repo
import com.example.medicalapp.Data.responce.GetAllUserResponce
import com.example.medicalapp.Data.responce.GetSpecificUserResponce
import com.example.medicalapp.Data.responce.LoginUserResponce

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

    private val _getSpecificUserState: MutableStateFlow<getSpecificUserState> =
        MutableStateFlow(getSpecificUserState())
    val getSpecificUserState = _getSpecificUserState.asStateFlow()


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
                        val userID = it.data?.body()?.user_id

                        Log.d("TAG", "signUpUser1: ${it.data?.body()?.user_id}")
                        Log.d("TAG", "signUpUser: $userID")
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
        userID: String
    ) {
        viewModelScope.launch {
            repo.getSpecificUser(
                userID = userID
            ).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _getSpecificUserState.value = getSpecificUserState(Loading = true)
                    }

                    is State.Success -> {
                        Log.d("TAG", "getSpecificUser: ${state.data?.body()}")
                        _getSpecificUserState.value =
                            getSpecificUserState(Data = state.data, Loading = false)
                    }

                    is State.Error -> {
                        _getSpecificUserState.value =
                            getSpecificUserState(error = state.message, Loading = false)
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
    val Data: Response<GetSpecificUserResponce>? = null,
    val error: String? = null
)