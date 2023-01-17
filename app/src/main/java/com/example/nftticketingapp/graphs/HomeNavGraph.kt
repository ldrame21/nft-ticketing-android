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
import com.example.nftticketingapp.ViewModel.BuyTicketViewModel
import com.example.nftticketingapp.ViewModel.CreateEventViewModel
import com.example.nftticketingapp.screens.home.MarketContent
import com.example.nftticketingapp.screens.home.MyTicketsContent
import com.example.nftticketingapp.screens.home.UserContent
import com.example.nftticketingapp.ViewModel.UserViewModel

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Market.route,
    ) {
        val viewModel = UserViewModel()
        val createEventViewModel = CreateEventViewModel()
        val buyTicketViewModel = BuyTicketViewModel()
        //val myTicketsViewModel = MyTicketsViewModel()
        //viewModel.setUserContent()
        composable(route = BottomBarScreen.User.route) {
            UserContent(username = "Robin", viewModel = viewModel, navController = navController)
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
        userNavGraph(navController = navController)
    }
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object User : BottomBarScreen(
        route = "USER",
        title = "USER",
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
