package com.example.nftticketingapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.Event
import com.example.nftticketingapp.DataClasses.MarketItem
import com.example.nftticketingapp.DataClasses.Ticket
import com.example.nftticketingapp.DataClasses.TicketEvent
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

    private val _ticketEventData = MutableLiveData<List<TicketEvent>>()
    val ticketEventData: LiveData<List<TicketEvent>>
        get() = _ticketEventData

    init {

        //Observe the market
        marketReference.addValueEventListener(object :
        ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val marketItems = snapshot.getValue(object : GenericTypeIndicator<HashMap<String, MarketItem>>() {
                })

                val yolo = 5

                eventsReference.get().addOnSuccessListener {
                    val events = it.getValue(object : GenericTypeIndicator<HashMap<String, Event>>() {
                    })

                    var ticketEventList = mutableListOf<TicketEvent>()
                    if (marketItems != null) {
                        for (item in marketItems){


                            val ticketID = item.value.ticketID
                            val eventID = item.value.eventID


                            val eventPriceUpdate = events!!.get(eventID)!!
                            eventPriceUpdate.price = item.value.price

                            ticketEventList.add(TicketEvent(event = eventPriceUpdate,
                                ticketID = ticketID))

                        }
                    }
                    _ticketEventData.value = ticketEventList


                }.addOnFailureListener{}

                val yololo = 5


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }



}