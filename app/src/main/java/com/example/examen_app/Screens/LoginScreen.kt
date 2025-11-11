package com.example.examen_app.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examen_app.AuthViewModel
import com.example.examen_app.Screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val primaryColor = Color(0xFF3F51B5)
    val errorColor = MaterialTheme.colorScheme.error
    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido de vuelta ",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = primaryColor,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Inicia sesión para continuar",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(32.dp))


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                leadingIcon = { Icon(Icons.Default.MailOutline, contentDescription = "Email Icon") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))


            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMessage.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    errorMessage,
                    color = errorColor,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp),
                    textAlign = TextAlign.Start
                )
            }

            Spacer(Modifier.height(32.dp))


            Button(
                onClick = {
                    if (viewModel.login(email, password)) {
                        errorMessage = ""
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    } else {
                        errorMessage = "Credenciales incorrectas"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                enabled = email.isNotBlank() && password.isNotBlank()
            ) {
                Text("Iniciar Sesión", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("¿No tienes cuenta?", color = Color.Gray)
                Spacer(Modifier.width(4.dp))
                TextButton(onClick = { navController.navigate(Screen.Signup.route) }) {
                    Text("Regístrate", color = primaryColor, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.weight(1f),
                    color = Color.LightGray,
                    thickness = 1.dp
                )
                Text(
                    text = " Or ",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Divider(
                    modifier = Modifier.weight(1f),
                    color = Color.LightGray,
                    thickness = 1.dp
                )
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { /* Lógica de Login con Facebook */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xE6D7D7D7)),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                // Aquí deberías usar un ícono real de Facebook (requiere una librería de íconos personalizada)
                Text("f", color = Color(0xFF1877F2), fontSize = 24.sp, fontWeight = FontWeight.Black)
                Spacer(Modifier.width(16.dp))
                Text("Login with Facebook", color = Color.Black)
            }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = { /* Lógica de Login con Google */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) { // Aquí deberías usar un ícono real de Google (requiere una librería de íconos personalizada)
                Text("G", color = Color.Red, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.width(16.dp))
                Text("Login with Google", color = Color.Gray)
            }


            Spacer(Modifier.height(24.dp))

        }
    }
}