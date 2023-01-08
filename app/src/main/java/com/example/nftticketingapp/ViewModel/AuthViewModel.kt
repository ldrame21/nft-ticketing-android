package com.example.nftticketingapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.Firebase.FirebaseAuthObject
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {

    private var firebaseAuth = FirebaseAuth.getInstance()

    fun actionLogin(username: String, password: String){

        FirebaseAuthObject.LogIn(username = username,
            password = password,
            firebaseAuth = firebaseAuth,
            onSucess = {},
            onFailure = {})

    }

    fun actionSignUp(username: String, password: String){

        FirebaseAuthObject.SignUp(username = username,
            password = password,
            firebaseAuth = firebaseAuth,
            onSucess = {},
            onFailure = {})
    }
}

