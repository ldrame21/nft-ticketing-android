package com.example.nftticketingapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.Event
import com.example.nftticketingapp.DataClasses.MarketItem
import com.example.nftticketingapp.DataClasses.Ticket
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MarketViewModel: ViewModel() {

    //lateinit var user: User
    private var firebaseAuth = FirebaseAuth.getInstance()
    private var databaseReference = FirebaseDatabase.getInstance(
        "https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app"
    )

    private var uid = firebaseAuth.currentUser?.uid.toString()
    private var marketReference = databaseReference.getReference("Market")
    private var ticketsReference = databaseReference.getReference("Tickets")
    private var eventsReference = databaseReference.getReference("Events")

    private val _ticketToEventData = MutableLiveData<HashMap<Any?, Event>>()
    val ticketToEventData: LiveData<HashMap<Any?, Event>>
        get() = _ticketToEventData

    init {

        //Observe the market
        marketReference.addValueEventListener(object :
        ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val marketItems = snapshot.getValue(object : GenericTypeIndicator<HashMap<String, MarketItem>>() {
                })

               //For each marketItem, get the corresponding event and create a <HashMap<Any?, Event>>
                //also, don't forget to set the price in Event
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }



}