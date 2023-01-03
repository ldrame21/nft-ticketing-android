package com.example.nftticketingapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

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

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = "SETTINGS",
        icon = Icons.Default.Settings
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