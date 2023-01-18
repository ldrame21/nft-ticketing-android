package com.example.nftticketingapp.ViewModel

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
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
    private var marketReference = databaseReference.getReference("Market")


    fun sellTicket(ticketID: String, eventID: String, price: Double, context: Context){

        //Write the ticket and its price to the market
        val ticketRef = marketReference.push().key

        if (ticketRef != null) {
            val newMarketItem = MarketItem(ticketID = ticketID,
                eventID = eventID,
                sellerID = userUID,
                price = price)
            marketReference.child(ticketRef).setValue(newMarketItem).addOnCompleteListener{

                if(it.isSuccessful){
                    val msg = "Ticket successfully added to the market"
                    Log.d(ContentValues.TAG, msg)
                    context.showToast(msg)

                }else{
                    Log.w(ContentValues.TAG, "Failed to add new ticket to the market", it.exception)

                }

            }
        }
    }

    private fun Context.showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

