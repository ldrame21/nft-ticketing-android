package com.example.nftticketingapp.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nftticketingapp.screens.ScreenContent
import com.example.nftticketingapp.screens.home.MarketContent
import com.example.nftticketingapp.screens.home.MyTicketsContent

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Market.route
    ) {

        composable(route = BottomBarScreen.Profile.route) {
            ScreenContent(
                name = BottomBarScreen.Profile.route,
                onClick = { }
            )
        }
        composable(route = BottomBarScreen.Market.route) {
            MarketContent(
                navController = navController
            )
        }
        composable(route = BottomBarScreen.MyTickets.route) {
            MyTicketsContent(
                navController = navController
            )
        }
        ticketNavGraph(navController = navController)
        eventNavGraph(navController = navController)
    }
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "PROFILE",
        icon = Icons.Default.Person
    )
    object Market : BottomBarScreen(
        route = "MARKET",
        title = "MARKET",
        icon = Icons.Default.ShoppingCart
    )
    object MyTickets : BottomBarScreen(
        route = "MYTICKETS",
        title = "MYTICKETS",
        icon = Icons.Default.Star
    )
}
