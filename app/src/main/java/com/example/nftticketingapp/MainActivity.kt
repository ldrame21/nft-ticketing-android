package com.example.nftticketingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.nftticketingapp.graphs.RootNavigationGraph
import com.example.nftticketingapp.ui.theme.NFTTicketingAppTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Setup firebase
        /*val databaseUrl = "https://nft-ticketing-app-default-rtdb.europe-west1.firebasedatabase.app"
        val app = FirebaseApp.initializeApp(this, FirebaseOptions.Builder()
            .setDatabaseUrl(databaseUrl)
            .build())*/




        setContent {
            NFTTicketingAppTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}
