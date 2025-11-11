package com.example.examen_app

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    // Simulamos una "base de datos" sencilla
    private val users = mutableMapOf<String, Pair<String, String>>() // email -> (password, name)


    fun registerUser(name: String, email: String, password: String) {
        users[email] = Pair(password, name)
    }

    fun login(email: String, password: String): Boolean {
        val user = users[email]
        return user != null && user.first == password
    }
}