package com.example.nftticketingapp.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.Firebase.FirebaseAuthObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel() {

    private var firebaseAuth = FirebaseAuth.getInstance()
    private var databaseReference = FirebaseDatabase.getInstance("https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app")



    //Mutable state which stores user login status. Val with _ is readable and writable
    //val without _ is only readable
    private val _userLoginStatus = MutableStateFlow<UserLoginStatus?>(null)
    val userLoginStatus = _userLoginStatus.asStateFlow()

    private val _userSignupStatus = MutableStateFlow<UserSignupStatus?>(null)
    val userSignupStatus = _userSignupStatus.asStateFlow()

    fun actionLoginFirebase(username: String, password: String){

        FirebaseAuthObject.LogIn(username = username,
            password = password,
            firebaseAuth = firebaseAuth,
            onSucess = {
                _userLoginStatus.value = UserLoginStatus.StatusSucesseful
            },
            onFailure = { //The result failure gets back to the composable
                _userLoginStatus.value = UserLoginStatus.StatusFailure(it)
            })
    }

    fun actionSignUpFirebase(username: String, password: String){

        FirebaseAuthObject.SignUp(username = username,
            password = password,
            firebaseAuth = firebaseAuth,
            databaseReference = databaseReference,
            onSucess = {
                _userSignupStatus.value = UserSignupStatus.StatusSucesseful
            },
            onFailure = { //The result failure gets back to the composable
                _userSignupStatus.value = UserSignupStatus.StatusFailure(it)
            })

        /*FirebaseAuthObject.CreateUserProfile(username = username,
            firebaseAuth = firebaseAuth,
            databaseReference = databaseReference,
            onSucess = {
                _userSignupStatus.value = UserSignupStatus.StatusSucesseful
            },
            onFailure = { //The result failure gets back to the composable
                _userSignupStatus.value = UserSignupStatus.StatusFailure(it)
            })*/

    }
}

sealed class UserLoginStatus{

    object StatusSucesseful: UserLoginStatus()
    class StatusFailure(val exception: Exception?): UserLoginStatus()

}

sealed class UserSignupStatus{

    object StatusSucesseful: UserSignupStatus()
    class StatusFailure(val exception: Exception?): UserSignupStatus()

}

