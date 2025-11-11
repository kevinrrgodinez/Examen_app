package com.example.examen_app.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examen_app.Screens.LoginScreen
import com.example.examen_app.Screens.SignupScreen
import com.example.examen_app.AuthViewModel
import com.example.examen_app.Screens.Screen

import com.example.examen_app.Screens.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController, viewModel: AuthViewModel) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(Screen.Signup.route) {
            SignupScreen(navController, viewModel)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController, viewModel)
        }

        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
    }
}