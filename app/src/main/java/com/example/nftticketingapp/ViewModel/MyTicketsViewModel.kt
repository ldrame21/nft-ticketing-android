package com.example.nftticketingapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MyTicketsViewModel: ViewModel() {

    //lateinit var user: User
    private var firebaseAuth = FirebaseAuth.getInstance()
    private var databaseReference = FirebaseDatabase.getInstance(
        "https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app"
    )

    private var uid = firebaseAuth.currentUser?.uid.toString()
    private var usersReference = databaseReference.getReference("Users")
    private var ticketsReference = databaseReference.getReference("Tickets")
    private var eventsReference = databaseReference.getReference("Events")



    private val _ticketEventData2 = MutableLiveData<List<TicketEvent>>()
    val ticketEventData2: LiveData<List<TicketEvent>>
        get() = _ticketEventData2

    init {

        if(uid.isNotEmpty())
        {
            val tokenListKey = "tokenList"
            //Observe user tokenList of the current user in the database
            usersReference.child(uid).child(tokenListKey).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    //When the tokenList changes, update the Events to be observed
                    //Get the user data whenever it is changed
                    //val user = snapshot.getValue(User::class.java)
                    //_userData.value = user

                    var tokenList = snapshot.getValue(object :
                        GenericTypeIndicator<HashMap<String?, String?>>() {
                    })
                    if (tokenList != null) {
                        //Get the ticket database
                        ticketsReference.get().addOnSuccessListener {
                            val tickets = it.getValue(object : GenericTypeIndicator<HashMap<String, Ticket>>() {
                            })

                            //Get the eventID for each ticket in the tokenList and create an hashmap ticketID -> eventID
                            var ticketToEventHash = HashMap<Any?, String>()
                            for (token in tokenList){
                                ticketToEventHash += token.value to tickets!!.get(token.value)!!.eventID
                            }

                            //Get the event database
                            eventsReference.get().addOnSuccessListener {
                                val events = it.getValue(object : GenericTypeIndicator<HashMap<String, Event>>() {
                                })

                                var ticketEventList2 = mutableListOf<TicketEvent>()
                                for (eventID in ticketToEventHash){

                                    val event_i = events?.get(eventID.value)
                                    event_i?.uid = eventID.value
                                    val ticket_i = tickets?.get(eventID.key.toString())

                                    ticketEventList2.add(TicketEvent(event = event_i,
                                        ticket = ticket_i))

                                }

                                _ticketEventData2.value = ticketEventList2


                            }.addOnFailureListener{}


                        }.addOnFailureListener{}



                    }
                    else {

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }


}