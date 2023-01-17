package com.example.nftticketingapp.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.nftticketingapp.DataClasses.TicketEvent
import com.example.nftticketingapp.screens.home.mytickets.TicketContent

fun NavGraphBuilder.ticketNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.TICKET,
        startDestination = TicketScreen.Ticket.route
    ) {

        composable(
            route = TicketScreen.Ticket.route
        ) {
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<TicketEvent>("ticket")
            TicketContent(
                ticketEvent = result
            )
        }
    }
}

sealed class TicketScreen(val route: String) {
    object Ticket : TicketScreen(route = "TICKET")
}