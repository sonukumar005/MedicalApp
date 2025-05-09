package com.example.medicalapp.Data

import com.example.medicalapp.Data.responce.GetAllUserResponce
import com.example.medicalapp.Data.responce.LoginUserResponce
import com.example.medicalapp.Data.responce.getSpecificUserResponce
import com.example.medicalapp.Data.responce.signUpResponce

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface apiService{

    @FormUrlEncoded
    @POST("signUp")
    suspend fun signUpUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone: String,
        @Field("address") address: String,
        @Field("pincode") pincode: String
    ): Response<signUpResponce>


    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginUserResponce>

    @FormUrlEncoded
    @POST("getSpecificUser")
    suspend fun getSpecificUser(
        @Field("userID") userId: String
    ): Response<getSpecificUserResponce>

}