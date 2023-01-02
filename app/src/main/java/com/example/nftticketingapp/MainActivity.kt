package com.example.nftticketingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.nftticketingapp.graphs.RootNavigationGraph
import com.example.nftticketingapp.ui.theme.NFTTicketingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NFTTicketingAppTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}
