package com.example.nftticketingapp.DataClasses

data class MarketItem(var ticketID: String, var price: Double){

    constructor(): this("", 0.0)

}
