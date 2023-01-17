package com.example.nftticketingapp.DataClasses


data class Ticket(var uid: String? = null,
                  var eventID: String,
                  var transactions: HashMap<String?, Transaction?> = HashMap()
){

    constructor(): this(null, "", HashMap())



}
