package com.example.nftticketingapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.*
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

    private val _ticketEventData2 = MutableLiveData<List<TicketEvent2>>()
    val ticketEventData2: LiveData<List<TicketEvent2>>
        get() = _ticketEventData2



    init {

        //Observe the market
        marketReference.addValueEventListener(object :
        ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val marketItems = snapshot.getValue(object : GenericTypeIndicator<HashMap<String, MarketItem>>() {
                })


                eventsReference.get().addOnSuccessListener {
                    val events = it.getValue(object : GenericTypeIndicator<HashMap<String, Event>>() {
                    })

                    var ticketEventList = mutableListOf<TicketEvent>()
                    var ticketEventList2 = mutableListOf<TicketEvent2>()
                    if (marketItems != null) {
                        for (item in marketItems){


                            val ticketID = item.value.ticketID
                            val eventID = item.value.eventID
                            val sellerID = item.value.sellerID


                            val eventUpdate = events!!.get(eventID)!!
                            val ticketUpdate = Ticket(uid = ticketID,
                                eventID = eventID,
                                marketID = item.key,
                                sellerID = sellerID,
                                price = item.value.price
                            )

                            eventUpdate.price = item.value.price
                            eventUpdate.uid = eventID
                            eventUpdate.sellerID = sellerID
                            eventUpdate.marketID = item.key

                            ticketEventList.add(TicketEvent(event = eventUpdate,
                                ticketID = ticketID))
                            ticketEventList2.add(TicketEvent2(event = eventUpdate,
                                ticket = ticketUpdate))

                        }
                    }
                    _ticketEventData.value = ticketEventList
                    _ticketEventData2.value = ticketEventList2


                }.addOnFailureListener{}

                val yololo = 5


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }



}