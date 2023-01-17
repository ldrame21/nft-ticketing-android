package com.example.nftticketingapp.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.nftticketingapp.screens.home.mytickets.TicketContent

const val TICKET_ARG_KEY = "name"
const val TICKET_ARG_KEY2 = "artist"

fun NavGraphBuilder.ticketNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.TICKET,
        startDestination = TicketScreen.Ticket.route
    ) {

        composable(
            route = TicketScreen.Ticket.route,
            arguments = listOf(
                navArgument(TICKET_ARG_KEY) {type = NavType.StringType},
                navArgument(TICKET_ARG_KEY2) {type = NavType.StringType}
            )
        ) {
            TicketContent(
                name = it.arguments?.getString(TICKET_ARG_KEY).toString(),
                artist = it.arguments?.getString(TICKET_ARG_KEY2).toString())
        }
    }
}

sealed class TicketScreen(val route: String) {
    object Ticket : TicketScreen(route = "TICKET/{$TICKET_ARG_KEY}/{$TICKET_ARG_KEY2}") {
        fun passNameAndArtist(
            name: String?,
            artist: String?
        ): String {
            return "TICKET/$name/$artist"
        }
    }
    object SellTicket : TicketScreen(route = "SELL_TICKET")
}