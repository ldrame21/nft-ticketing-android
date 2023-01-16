package com.example.nftticketingapp.ViewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.Event
import com.example.nftticketingapp.DataClasses.Ticket
import com.example.nftticketingapp.DataClasses.Transaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue

class CreateEventViewModel(): ViewModel() {


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: FirebaseDatabase
    private lateinit var userUID: String

    init {

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance("https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app")
        userUID = firebaseAuth.currentUser?.uid.toString()

    }


    fun addEvent(event: Event) {

        //Show a sign up sucess message
        Log.d(ContentValues.TAG, "Minting the event")
        println("Minting the event")


        //Push the new event in the Events tree
        val newEventRef = databaseReference.getReference("Events").push()
        val eventKey = newEventRef.key.toString()
        newEventRef.setValue(event).addOnCompleteListener{

            if(it.isSuccessful){

                //Create the tickets
                Log.d(ContentValues.TAG, "Event successfully created")


            }else{
                Log.w(ContentValues.TAG, "Failed to add new event in database", it.exception)

            }
        }

        val ticketList = mutableListOf<Ticket>()

        for(i in 1..event.numberOfTickets){

            /*ticketList.add(Ticket(
                transactions = listOf(hashMapOf("from" to "000000", "to" to "currentUser")),
                eventID = eventKey
            ))*/

            val newTicketRef = databaseReference.getReference("Tickets").push()
            val newTicketKey = newTicketRef.key
            val newTransRef = newTicketRef.child("transactions").push()
            //val newTransKey = newTransRef.key
            val ticket = Ticket(
                uid = newTicketKey,
                eventID = eventKey
            )
            //Add ticket to database
            newTicketRef.setValue(ticket).addOnCompleteListener{

                if(it.isSuccessful){

                    Log.d(ContentValues.TAG, "Ticket successfully created")

                }else{
                    Log.w(ContentValues.TAG, "Failed to add new ticket in database", it.exception)

                }

            }
            val a = Transaction(from ="000000", to = userUID, time = System.currentTimeMillis())
            //Add transaction to database
            newTransRef.setValue(
                Transaction(from ="000000", to = userUID, time = System.currentTimeMillis())
            ).
            addOnCompleteListener{

                if(it.isSuccessful){

                    Log.d(ContentValues.TAG, "Ticket successfully created")

                }else{
                    Log.w(ContentValues.TAG, "Failed to add new ticket in database", it.exception)

                }

            }

            //Add ticket to user wallet
            val userRef =  databaseReference.getReference("Users")
            val tokenListRef =userRef.child(userUID).child("tokenList")

            val newTokenRef = tokenListRef.push()
            val newTokenKey = newTokenRef.key
            val updates =
                mapOf<String?, String?>(
                    newTokenKey to newTicketKey
                )


            tokenListRef.updateChildren(updates) { databaseError, _ ->
                if (databaseError != null) {
                    Log.w(ContentValues.TAG, "Error updating token list", databaseError.toException())
                } else {
                    Log.d(ContentValues.TAG, "Successfully added new token to list")
                }
            }





        }
    }
}



