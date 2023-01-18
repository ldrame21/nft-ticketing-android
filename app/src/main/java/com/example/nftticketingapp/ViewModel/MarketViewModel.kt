package com.example.nftticketingapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.*
import com.google.firebase.database.*

class MarketViewModel: ViewModel() {

    private var databaseReference = FirebaseDatabase.getInstance(
        "https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app"
    )

    private var marketReference = databaseReference.getReference("Market")
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

                eventsReference.get().addOnSuccessListener {
                    val events = it.getValue(object : GenericTypeIndicator<HashMap<String, Event>>() {
                    })

                    var ticketEventList2 = mutableListOf<TicketEvent>()
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

                            ticketEventList2.add(TicketEvent(event = eventUpdate,
                                ticket = ticketUpdate))
                        }
                    }
                    _ticketEventData.value = ticketEventList2

                }.addOnFailureListener{}
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }



}