package com.example.nftticketingapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.Firebase.FirebaseAuthObject
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel() {

    private var firebaseAuth = FirebaseAuth.getInstance()


    //Mutable state which stores user login status. Val with _ is readable and writable
    //val without _ is only readable
    private val _userLoginStatus = MutableStateFlow<UserLoginStatus?>(null)
    val userLoginStatus = _userLoginStatus.asStateFlow()

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
            onSucess = {},
            onFailure = {})
    }
}

sealed class UserLoginStatus{

    object StatusSucesseful: UserLoginStatus()
    class StatusFailure(val exception: Exception?): UserLoginStatus()

}

