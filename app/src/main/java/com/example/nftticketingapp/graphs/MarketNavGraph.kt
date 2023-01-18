package com.example.nftticketingapp.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.nftticketingapp.DataClasses.TicketEvent
import com.example.nftticketingapp.DataClasses.TicketEvent2
import com.example.nftticketingapp.screens.home.market.BuyTicketContent

fun NavGraphBuilder.eventNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.EVENT,
        startDestination = EventScreen.Event.route
    ) {
        composable(
            route = EventScreen.Event.route

        ) {
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<TicketEvent2>("event")
            BuyTicketContent(
                ticketEvent = result,
                navController = navController
            )
        }
    }
}

sealed class EventScreen(val route: String) {
    object Event : EventScreen(route = "EVENT")
}