package com.example.nftticketingapp.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Ticket(var uid: String? = null,
                  var eventID: String,
                  var transactions: HashMap<String?, Transaction?> = HashMap(),
                  var price: Double? = null,
                  var sellerID: String? = null,
                  var marketID: String? = null
): Parcelable
{

    constructor(): this(null, "", HashMap())



}
