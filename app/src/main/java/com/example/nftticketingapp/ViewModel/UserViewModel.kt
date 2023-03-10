package com.example.nftticketingapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseError

class UserViewModel: ViewModel() {


    //lateinit var user: User
    private var firebaseAuth = FirebaseAuth.getInstance()
    private var databaseReference = FirebaseDatabase.getInstance(
        "https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app"
    )

    private var uid = firebaseAuth.currentUser?.uid.toString()
    private var usersReference = databaseReference.getReference("Users")



    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User>
        get() = _userData

    init {
    /*FirebaseAuthObject.getUserData(firebaseAuth = firebaseAuth,
            databaseReference = databaseReference)*/
        if(uid.isNotEmpty())
        {

            val keyToCheck = "balance"

            usersReference.child(uid).child(keyToCheck).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // The key exists in the child node
                        println("The key $keyToCheck exists in the child node")
                    } else {

                        println("The key $keyToCheck does not exist in the child node")
                        //Add to the Users database the initial balance of 0 if this field does not exist yet
                        val childUpdates = hashMapOf<String, Any>(
                            "$uid/$keyToCheck" to 0
                        )
                        usersReference.updateChildren(childUpdates)
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })

            /*val keyToCheck2 = "tickets"

            usersReference.child(uid).child(keyToCheck2).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // The key exists in the child node
                        println("The key $keyToCheck2 exists in the child node")
                    } else {

                        println("The key $keyToCheck2 does not exist in the child node")
                        //Add to the Users database the initial balance of 0 if this field does not exist yet

                        val childUpdates = hashMapOf<String, Any>(
                            "$uid/$keyToCheck2" to listOf<String>("")
                        )
                        usersReference.updateChildren(childUpdates)
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })*/


            //Observe current User in database
            usersReference.child(uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    //Get the user data whenever it is changed
                    val user = snapshot.getValue(User::class.java)
                    _userData.value = user

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }


    // Wallet Backend
    /*var balance by mutableStateOf(50.0)
        private set*/

    fun addBalance(amount: Double){
        //_userData.value!!.balance += amount
        //balance += amount
        //TODO : Ajouter le refresh de la database
        val balanceKey = "balance"
        usersReference.child(uid).child(balanceKey)

        val childUpdates = hashMapOf<String, Any>(
            "$uid/$balanceKey" to _userData.value!!.balance + amount
        )

        usersReference.updateChildren(childUpdates)

    }

    // Popup for wallet loading
    var isDialogShown by mutableStateOf(false)
        private set
    fun onLoadWalletClick(){
        isDialogShown = true
    }
    fun onDismissDialog(){
        isDialogShown = false
    }
}