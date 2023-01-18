package com.example.nftticketingapp.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(val from: String,
                  val to: String,
                  val time: Long): Parcelable
{

    constructor(): this("", "", 0)


}
