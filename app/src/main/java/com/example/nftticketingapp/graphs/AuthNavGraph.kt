package com.example.nftticketingapp.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.nftticketingapp.screens.LoginContent
import com.example.nftticketingapp.screens.ScreenContent
import com.example.nftticketingapp.screens.home.LoginScreen
import com.example.nftticketingapp.screens.home.SignupScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {

            LoginScreen(onLogInSucessful = {
                navController.popBackStack()
                navController.navigate(Graph.HOME)
            },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                })
        }
        composable(route = AuthScreen.SignUp.route) {
            SignupScreen(onSignUpSuccesful = {
                navController.popBackStack()
                navController.navigate(AuthScreen.Login.route)
            },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                })
        }

        composable(route = AuthScreen.Forgot.route) {
            ScreenContent(name = AuthScreen.Forgot.route) {}
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}