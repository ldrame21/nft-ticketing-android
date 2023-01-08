package com.example.nftticketingapp

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.nftticketingapp.graphs.RootNavigationGraph
import com.example.nftticketingapp.ui.theme.NFTTicketingAppTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    //private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NFTTicketingAppTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }

        //mAuth = FirebaseAuth.getInstance()
        //val user = mAuth.currentUser

    }
}
