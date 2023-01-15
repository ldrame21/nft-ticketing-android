package com.example.nftticketingapp.ViewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class BuyTicketViewModel: ViewModel() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: FirebaseDatabase
    private lateinit var userUID: String

    init {

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance("https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app")
        userUID = firebaseAuth.currentUser?.uid.toString()

    }

    fun buyTicket(ticketRef: String, from: String){
        //if (verifyTransaction()) {
        if(true){

           //if(verifyUserBalance()){
            if(true){

                addTransaction(ticketRef = ticketRef, from = from)
                //transferToken()
                //updateUsersBalance()

            }
        }
    }

    private fun updateUsersBalance() {
        TODO("Update buyer and seller balances")
    }


    private fun verifyTransaction(): Boolean{

        TODO("Chercher dans la base de donnée si from est bien l'owner du ticket")

    }

    private fun addTransaction(ticketRef: String, from: String){

        val newTransRef =  databaseReference.getReference("Tickets").
        child(ticketRef).child("transactions").push()

        newTransRef.setValue(hashMapOf("from" to from, "to" to userUID)).
        addOnCompleteListener{

            if(it.isSuccessful){

                Log.d(ContentValues.TAG, "Ticket successfully created")

            }else{
                Log.w(ContentValues.TAG, "Failed to add new ticket in database", it.exception)

            }

        }
    }


    private fun transferToken() {
        TODO("Not yet implemented")
    }

    private fun verifyUserBalance(): Boolean {

        TODO()

    }
}
