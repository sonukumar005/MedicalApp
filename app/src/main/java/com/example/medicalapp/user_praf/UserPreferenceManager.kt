package com.example.medicalapp.user_praf

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")
 class UserPreferencesManager(private val context: Context) {
     companion object{
         private val USER_ID_KEYLogin = stringPreferencesKey("user_id")

         private val USER_ID_KEYSignUp = stringPreferencesKey("user_id")
     }


     suspend fun saveUserIdLogin(userId: String){
         context.dataStore.edit {
             it[USER_ID_KEYLogin] = userId
         }
     }
     suspend fun saveUserIdSignUp(userId: String){
         context.dataStore.edit {
             it[USER_ID_KEYSignUp] = userId
         }
     }


     val userIdLogin:Flow<String?> = context.dataStore.data.map{
         it[USER_ID_KEYLogin]
     }

        val userIdSignUp:Flow<String?> = context.dataStore.data.map{
            it[USER_ID_KEYSignUp]
        }


     suspend fun clearUserIDLogin(){
         context.dataStore.edit{
             it.remove(USER_ID_KEYLogin)
         }
     }

        suspend fun clearUserIDSignUp(){
            context.dataStore.edit{
                it.remove(USER_ID_KEYSignUp)
            }
        }

 }