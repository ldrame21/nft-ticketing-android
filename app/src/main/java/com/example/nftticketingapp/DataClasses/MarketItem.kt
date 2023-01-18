package com.example.nftticketingapp.DataClasses

data class MarketItem(var ticketID: String,
                      var eventID: String,
                      var sellerID: String,
                      var price: Double){


    //Required by firebase to get the data
    constructor(): this("", "", "", 0.0)

}
