package com.example.nftticketingapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.Event
import com.example.nftticketingapp.DataClasses.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyTicketsViewModel: ViewModel() {

    //lateinit var user: User
    private var firebaseAuth = FirebaseAuth.getInstance()
    private var databaseReference = FirebaseDatabase.getInstance(
        "https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app"
    )

    private var uid = firebaseAuth.currentUser?.uid.toString()
    private var usersReference = databaseReference.getReference("Users")



    private val _eventData = MutableLiveData<Event>()
    val userData: LiveData<Event>
        get() = _eventData

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
                    val yolo = 5

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }


}