package com.example.nftticketingapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainViewModel: ViewModel() {

    //private val dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")


    // Wallet Backend
    var balance by mutableStateOf(50.0)
        private set
    fun addBalance(amount: Double){
        balance += amount
        //TODO : Ajouter le refresh de la database
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