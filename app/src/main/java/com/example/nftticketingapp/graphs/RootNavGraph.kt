package com.example.nftticketingapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nftticketingapp.screens.home.HomeScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        //Calling this function creates the Authentification Navigation Graph
        authNavGraph(navController = navController)
        //composable add a screen in this root nav graph. In this case, we add the home screen
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}