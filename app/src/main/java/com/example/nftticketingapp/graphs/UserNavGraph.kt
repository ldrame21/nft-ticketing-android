package com.example.nftticketingapp.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.nftticketingapp.screens.home.user.CreateEventContent

fun NavGraphBuilder.userNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.USER,
        startDestination = UserScreen.CreateEvent.route
    ) {
        composable(route = UserScreen.CreateEvent.route) {
            CreateEventContent(navController = navController)
        }
    }
}

sealed class UserScreen(val route: String) {
    object CreateEvent : UserScreen(route = "CREATE_EVENT")
}