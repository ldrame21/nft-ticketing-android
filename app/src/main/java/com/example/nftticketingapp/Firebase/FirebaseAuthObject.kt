package com.example.nftticketingapp.Firebase
import android.content.ContentValues.TAG
import android.util.Log
import com.example.nftticketingapp.DataClasses.User
import  com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

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
               databaseReference: FirebaseDatabase,
               onSucess: () -> Unit,
               onFailure: (Exception?) -> Unit){

        firebaseAuth.createUserWithEmailAndPassword(username, password).
        addOnCompleteListener{
            if(it.isSuccessful){
                //Show a sign up sucess message
                Log.d(TAG, "User successfully created")

                //Extract the user id as it must be the same as the one used to auth the user
                val uid = firebaseAuth.currentUser?.uid
                // Create new user
                val user = User(username = username)

                // Access the Users location in the database or create it if it doesn't exist
                //databaseReference.getReference("Users")

                if(uid != null){
                    //addOnCompleteListener gets notified when the task is completed.
                    databaseReference.getReference("Users").child(uid).setValue(user).addOnCompleteListener{

                        if(it.isSuccessful){

                            Log.d(TAG, "User successfully created")

                        }else{
                            Log.w(TAG, "Failed to add new user in database", it.exception)

                        }
                    }
                onSucess()

                } else{

                Log.w(TAG, "Failed to create new user", it.exception)
                onFailure(it.exception)
                /*Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()*/
                }
            }
        }

    /*fun CreateUserProfile(username: String,
                          firebaseAuth: FirebaseAuth,
                          databaseReference: FirebaseDatabase,
                          onSucess: () -> Unit,
                          onFailure: (Exception?) -> Unit){


    }*/
    }
}