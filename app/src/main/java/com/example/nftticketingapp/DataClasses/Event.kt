package com.example.nftticketingapp.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    var name: String,
    var numberOfTickets: Int,
    var price: Double = 0.0,
    var artist: String,
    var address: String,
    var date: String,
    var description: String,
    var uid: String? = null,
    var sellerID: String? = null,
    var marketID: String? = null
): Parcelable
{
    constructor(): this("", 0, 0.0, "", "", "", "")
}