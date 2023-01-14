package com.example.nftticketingapp.DataClasses

data class Event2(
    var name: String,
    var numberOfTickets: Int,
    var price: Double = 0.0,
    var artist: String,
    var address: String,
    var date: String,
    var description: String,
    var uid: String? = null
){
    init {

    }
}