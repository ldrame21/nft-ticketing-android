package com.example.nftticketingapp.Firebase
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import  com.google.firebase.auth.FirebaseAuth

object FirebaseAuthObject{

    fun LogIn(username: String,
              password: String,
              firebaseAuth: FirebaseAuth,
              onSucess: ()-> Unit,
              onFailure: (Exception?) -> Unit
    ){

        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener{

                if(it.isSuccessful){
                    onSucess()
                } else{
                    onFailure(it.exception)

                }

            }
    }

    fun SignUp(username: String,
               password: String,
               firebaseAuth: FirebaseAuth,
               onSucess: () -> Unit,
               onFailure: (Exception?) -> Unit){

        firebaseAuth.createUserWithEmailAndPassword(username, password).
        addOnCompleteListener{
            if(it.isSuccessful){
                //Show a sign up sucess message
                Log.d(TAG, "User successfully created")
                val newUser = firebaseAuth.currentUser

            } else{

                Log.w(TAG, "Failed to create new user", it.exception)
                /*Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()*/

            }

        }
    }
}