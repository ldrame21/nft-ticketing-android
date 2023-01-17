package com.example.nftticketingapp.DataClasses

data class Transaction(val from: String,
                  val to: String,
                  val time: Long){

    constructor(): this("", "", 0)


}
