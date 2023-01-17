package com.example.nftticketingapp.ViewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.MarketItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SellTicketViewModel: ViewModel() {

    private var firebaseAuth = FirebaseAuth.getInstance()
    private var databaseReference = FirebaseDatabase.getInstance(
        "https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app"
    )

    private var userUID = firebaseAuth.currentUser?.uid.toString()
    private var ticketsReference = databaseReference.getReference("Tickets")
    private var eventsReference = databaseReference.getReference("Events")
    private var marketReference = databaseReference.getReference("Market")


    fun sellTicket(ticketID: String, eventID: String, price: Double){


        //verifyTransaction()

        //Write the ticket and its price to the market
        val ticketRef = marketReference.push().key

        if (ticketRef != null) {
            val newMarketItem = MarketItem(ticketID = ticketID, eventID = eventID, price = price)
            marketReference.child(ticketRef).setValue(newMarketItem).addOnCompleteListener{

                if(it.isSuccessful){

                    Log.d(ContentValues.TAG, "Ticket successfully added to the market")

                }else{
                    Log.w(ContentValues.TAG, "Failed to add new ticket to the market", it.exception)

                }

            }
        }





    }

    private fun verifyTransaction() {
        TODO("Not yet implemented")
    }
}