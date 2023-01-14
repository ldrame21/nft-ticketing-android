package com.example.nftticketingapp.ViewModel

import android.content.ContentValues
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class EventViewModel(): ViewModel() {


    private var firebaseAuth = FirebaseAuth.getInstance()
    private var databaseReference = FirebaseDatabase.getInstance("https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app")


    fun addEvent(event: Event) {

        //Show a sign up sucess message
        Log.d(ContentValues.TAG, "Minting the event")
        println("Minting the event")


        //addOnCompleteListener gets notified when the task is completed.
        databaseReference.getReference("Events").push().setValue(event).addOnCompleteListener{

            if(it.isSuccessful){

                Log.d(ContentValues.TAG, "Event successfully created")

            }else{
                Log.w(ContentValues.TAG, "Failed to add new event in database", it.exception)

            }
        }
    }




}