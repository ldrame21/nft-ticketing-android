package com.example.nftticketingapp.ViewModel

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
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

    fun buyTicket(ticketRef: String,
                  marketID: String,
                  from: String,
                  ticketPrice: Double,
                  context: Context){

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

            //Verify that buyer has enough money on his account
            //verifyUserBalance(ticketPrice = ticketPrice)
            val usersReference = databaseReference.getReference("Users")
            val balanceKey = "balance"
            val tokenListKey = "tokenList"

            usersReference.get().addOnSuccessListener {
                val users = it.getValue(object : GenericTypeIndicator<HashMap<String, User>>() {
                })

                val seller = users?.get(from)
                val buyer = users?.get(userUID)

                val sellerBalance = seller!!.balance
                val buyerBalance = buyer!!.balance
                val sellerTokenList = seller.tokenList
                val buyerTokenList = buyer.tokenList

                when{

                    buyerBalance >= ticketPrice -> {
                        Log.i("Balance", "User has enough money ${buyerBalance}")

                        //The token is transfered between seller and buyer
                        val sellerTicketRefKey = sellerTokenList.filter { ticketRef == it.value }.keys.first()
                        sellerTokenList.remove(sellerTicketRefKey)
                        buyerTokenList[sellerTicketRefKey] = ticketRef

                        val childUpdates = hashMapOf<String, Any>(
                            //Money is transfered between buyer and seller
                            "$userUID/$balanceKey" to buyerBalance - ticketPrice,
                            "$from/$balanceKey" to sellerBalance + ticketPrice,
                            "$userUID/$tokenListKey" to buyerTokenList,
                            "$from/$tokenListKey" to sellerTokenList
                        )

                        usersReference.updateChildren(childUpdates)

                        addTransaction(ticketRef = ticketRef, from = from, context = context)

                        //Ici on va remove le ticket de la marketDatabase
                        //removeTokenFromMarket()
                        val marketReference = databaseReference.getReference("Market")
                        marketReference.child(marketID).removeValue()
                    }

                    else -> {
                        val msg = "User doesn't have enough money ${buyerBalance}"
                        Log.i("Balance", msg)
                        context.showToast(msg)
                    }
                }


            }


        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun addTransaction(ticketRef: String, from: String, context: Context){

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
                val msg = "Ticket successfully created"
                context.showToast("Ticket successfully bought !")
                Log.d(ContentValues.TAG, msg)

            }else{
                val msg = "Failed to add new ticket in database"
                Log.w(ContentValues.TAG, msg, it.exception)

            }

        }
    }

    private fun Context.showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
