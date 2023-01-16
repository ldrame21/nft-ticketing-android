package com.example.nftticketingapp.ViewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.nftticketingapp.DataClasses.Transaction
import com.example.nftticketingapp.DataClasses.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class BuyTicketViewModel: ViewModel() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: FirebaseDatabase
    private lateinit var userUID: String

        init {

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance("https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app")
        userUID = firebaseAuth.currentUser?.uid.toString()

    }

    fun buyTicket(ticketRef: String, from: String, ticketPrice: Double){

        val transRef = databaseReference.getReference("Tickets")
            .child(ticketRef).child("transactions")

        //Verifying the transaction history of the ticket
        transRef.get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            val transactions = it.value


            //val transKeys = (transactions as HashMap<*,*>).keys

            /*for(key in transKeys){
                TODO("VERIFIER LA TRANSACTION")
            }*/

            //Ici on va vérifier dans usersDatabase que userUID a assez d'argent
            //verifyUserBalance(ticketPrice = ticketPrice)
            val usersReference = databaseReference.getReference("Users")
            val balanceKey = "balance"
            val tokenListKey = "tokenList"

            usersReference.get().addOnSuccessListener {
                val users = it.getValue(object : GenericTypeIndicator<HashMap<String, User>>() {
                })
                val yolololo = 5
                val seller = users?.get(from)
                val buyer = users?.get(userUID)

                val sellerBalance = seller!!.balance
                val buyerBalance = buyer!!.balance
                val sellerTokenList = seller.tokenList
                val buyerTokenList = buyer.tokenList

                when{

                    buyerBalance >= ticketPrice -> {
                        Log.i("Balance", "User has enough money ${buyerBalance}")

                        //The tokenList are updated
                        val sellerTicketRefKey = sellerTokenList.filter { ticketRef == it.value }.keys.first()
                        sellerTokenList.remove(sellerTicketRefKey)
                        buyerTokenList[sellerTicketRefKey] = ticketRef



                        val childUpdates = hashMapOf<String, Any>(
                            "$userUID/$balanceKey" to buyerBalance - ticketPrice,
                            "$from/$balanceKey" to sellerBalance + ticketPrice,
                            "$userUID/$tokenListKey" to buyerTokenList,
                            "$from/$tokenListKey" to sellerTokenList
                        )

                        usersReference.updateChildren(childUpdates)
                        val c = 5


                    }

                    else -> {

                        Log.i("Balance", "User doesn't have enough money ${it.value.toString()}")

                    }
                }


            }

            //Ici on va ajouter les tokens dans tokenlist dans userDatabase
            //transferToken(ticketRef = ticketRef, from = from)
            //Ici on va modifier leurs balance dans userDatabase
            //updateUsersBalance()
            //Ici on va ajouter la transaction dans ticketDatabase
            //addTransaction(ticketRef = ticketRef, from = from)
            //Ici on va remove le ticket dans marketDatabase
            //removeTokenFromMarket()
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun removeTokenFromMarket() {
        TODO("Not yet implemented")
    }

    private fun updateUsersBalance() {
        TODO("Update buyer and seller balances")
    }

    private fun addTransaction(ticketRef: String, from: String){

        val newTransRef =  databaseReference.getReference("Tickets").
        child(ticketRef).child("transactions").push()

        /*newTransRef.setValue(hashMapOf("from" to from,
            "to" to userUID,
            "time" to ServerValue.TIMESTAMP)).*/
        newTransRef.setValue(
            Transaction(from = from, to = userUID, time = System.currentTimeMillis())
        ).
        addOnCompleteListener{

            if(it.isSuccessful){

                Log.d(ContentValues.TAG, "Ticket successfully created")

            }else{
                Log.w(ContentValues.TAG, "Failed to add new ticket in database", it.exception)

            }

        }
    }


    private fun transferToken(ticketRef: String, from: String) {
        TODO("Not yet implemented")
    }

    private fun verifyUserBalance(ticketPrice: Double): Boolean {

        TODO()

    }
}
