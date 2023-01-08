package com.example.nftticketingapp.Firebase
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

        firebaseAuth.signInWithEmailAndPassword(username, password).
        addOnCompleteListener{
            if(it.isSuccessful){
                onSucess()
            } else{
                onFailure(it.exception)
            }
        }
    }
}