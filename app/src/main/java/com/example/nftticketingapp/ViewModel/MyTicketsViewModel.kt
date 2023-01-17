package com.example.nftticketingapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.Event
import com.example.nftticketingapp.DataClasses.Ticket
import com.example.nftticketingapp.DataClasses.User
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



    private val _ticketToEventData = MutableLiveData<HashMap<Any?, Event>>()
    val ticketToEventData: LiveData<HashMap<Any?, Event>>
        get() = _ticketToEventData

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

                                //hashMapOf(token to tickets!!.get(token.value).eventID)
                                val yolo = 5
                                ticketToEventHash += token.value to tickets!!.get(token.value)!!.eventID

                                //Get the eventUID of the ticket
                                /*ticketsReference.child("ticketRef").child('eventID')
                                    .addListenerForSingleValueEvent{

                                    }*/

                            }


                            //Get the event database
                            eventsReference.get().addOnSuccessListener {
                                val events = it.getValue(object : GenericTypeIndicator<HashMap<String, Event>>() {
                                })

                                var ticketIDToEventHash = HashMap<Any?, Event>()
                                for (eventID in ticketToEventHash){

                                    ticketIDToEventHash += eventID.key to events!!.get(eventID.value)!!
                                    val yolo = 5

                                }
                                _ticketToEventData.value = ticketIDToEventHash


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