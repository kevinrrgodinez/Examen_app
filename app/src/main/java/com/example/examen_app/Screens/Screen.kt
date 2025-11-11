package com.example.examen_app.Screens


sealed class Screen(val route: String) {
    object Signup : Screen("signup")
    object Login : Screen("login")
    object Welcome : Screen("Welcome")
}