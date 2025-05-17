package com.example.medicalapp.Data.Repo

import android.util.Log
import com.example.medicalapp.Data.apiProvider
import com.example.medicalapp.Data.responce.GetAllUserResponce
import com.example.medicalapp.Data.responce.GetSpecificUserResponce
import com.example.medicalapp.Data.responce.LoginUserResponce

import com.example.medicalapp.Data.responce.signUpResponce
import com.example.medicalapp.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class repo {
    val apiService = apiProvider.providerApi()

    suspend fun signUpUser(
        name: String,
        email: String,
        password: String,
        phone: String,
        address: String,
        pincode: String
    ): Flow<State<Response<signUpResponce>>> = flow {
        emit(State.Loading)
        try {
            val response = apiService.signUpUser(
                name = name,
                email = email,
                password = password,
                phone = phone,
                address = address,
                pincode = pincode
            )

            emit(State.Success(response))

        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }


    }

    suspend fun loginUser(
        email: String,
        password: String
    ): Flow<State<Response<LoginUserResponce>>> = flow {
        emit(State.Loading)
        try {
            val response = apiService.loginUser(
                email = email,
                password = password
            )

            emit(State.Success(response))
            Log.d("TAG3", "loginUser: ${response.body()?.message}")

        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }

    }

    suspend fun getSpecificUser(
        userID: String
    ): Flow<State<Response<GetSpecificUserResponce>>> = flow {
        emit(State.Loading)
        try {
            val response = apiService.getSpecificUser(
                user_id = userID
            )

            emit(State.Success(response))


        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }

    }
}