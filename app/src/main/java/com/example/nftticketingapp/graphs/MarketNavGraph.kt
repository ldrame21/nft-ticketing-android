package com.example.nftticketingapp.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.nftticketingapp.screens.home.market.BuyTicketContent

const val EVENT_ARG_KEY = "name"
const val EVENT_ARG_KEY2 = "artist"

fun NavGraphBuilder.eventNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.EVENT,
        startDestination = EventScreen.Event.route
    ) {
        composable(
            route = EventScreen.Event.route,
            arguments = listOf(
                navArgument(EVENT_ARG_KEY) {type = NavType.StringType},
                navArgument(EVENT_ARG_KEY2) {type = NavType.StringType}
            )
        ) {
            BuyTicketContent(
                name = it.arguments?.getString(EVENT_ARG_KEY).toString(),
                artist = it.arguments?.getString(EVENT_ARG_KEY2).toString())
        }
    }
}

sealed class EventScreen(val route: String) {
    object Event : EventScreen(route = "EVENT/{$EVENT_ARG_KEY}/{$EVENT_ARG_KEY2}") {
        fun passNameAndArtist(
            name: String,
            artist: String
        ): String {
            return "EVENT/$name/$artist"
        }
    }
}