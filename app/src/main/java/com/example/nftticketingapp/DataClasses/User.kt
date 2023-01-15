package com.example.nftticketingapp.DataClasses

data class User(var username: String? = null, var uid: String? = null, var balance: Double = 1.0,
var tokenList: HashMap<String?, String?> = HashMap()
){



}
