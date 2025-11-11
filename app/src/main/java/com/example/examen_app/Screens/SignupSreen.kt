package com.example.examen_app.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examen_app.AuthViewModel
import com.example.examen_app.Screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavController, viewModel: AuthViewModel) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmError by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }


    val primaryColor = Color(0xFF3F51B5)
    val errorColor = MaterialTheme.colorScheme.error // Usamos el color de error del tema
    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    val allowedCharsRegex = Regex("[a-zA-Z0-9\\s]*")
    fun validate(): Boolean {
        var valid = true
        val specialCharCheck = Regex(".*[^a-zA-Z0-9\\s].*")
        nameError = if (name.isBlank()) { valid = false; "El nombre es requerido" } else if (specialCharCheck.matches(name)) { valid = false; "No se permiten caracteres especiales." }
        else ""
        emailError = if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false; "El email es requerido" // Mensaje simplificado
        } else ""
        passwordError = if (password.length < 6) {
            valid = false; "La contraseña es requerida" // Mensaje simplificado
        } else ""
        confirmError = if (confirmPassword != password || confirmPassword.isBlank()) { // Agregamos blank para que no salga vacío
            valid = false; "La confirmación es requerida" // Mensaje simplificado
        } else ""
        return valid
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {IconButton(
        onClick = { navController.navigate(Screen.Login.route) },
        modifier = Modifier
            .align(Alignment.TopStart)
            .padding(16.dp)
    ) {
         Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Volver a Iniciar Sesión",
            tint = primaryColor,
            modifier = Modifier.size(30.dp)
        )
    }
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Crea tu cuenta ",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = primaryColor,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Únete a la plataforma para empezar",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(32.dp))


            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Name Icon") },
                isError = nameError.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (nameError.isNotEmpty()) errorColor else primaryColor,
                    unfocusedBorderColor = if (nameError.isNotEmpty()) errorColor else MaterialTheme.colorScheme.outline,
                    focusedLabelColor = if (nameError.isNotEmpty()) errorColor else primaryColor,
                    unfocusedLabelColor = if (nameError.isNotEmpty()) errorColor else MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            if (nameError.isNotEmpty()) {
                Text(nameError, color = errorColor, fontSize = 12.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
            }
            Spacer(Modifier.height(16.dp))


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }, // Texto del label como en la imagen
                leadingIcon = { Icon(Icons.Default.MailOutline, contentDescription = "Email Icon") },
                isError = emailError.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (emailError.isNotEmpty()) errorColor else primaryColor,
                    unfocusedBorderColor = if (emailError.isNotEmpty()) errorColor else MaterialTheme.colorScheme.outline,
                    focusedLabelColor = if (emailError.isNotEmpty()) errorColor else primaryColor,
                    unfocusedLabelColor = if (emailError.isNotEmpty()) errorColor else MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            if (emailError.isNotEmpty()) {
                Text(emailError, color = errorColor, fontSize = 12.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
            }
            Spacer(Modifier.height(16.dp))


            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }, // Texto del label como en la imagen
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.Clear else Icons.Filled.Check
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(icon, contentDescription = if (passwordVisible) "Ocultar Contraseña" else "Ver Contraseña")
                    }
                },
                isError = passwordError.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (passwordError.isNotEmpty()) errorColor else primaryColor,
                    unfocusedBorderColor = if (passwordError.isNotEmpty()) errorColor else MaterialTheme.colorScheme.outline,
                    focusedLabelColor = if (passwordError.isNotEmpty()) errorColor else primaryColor,
                    unfocusedLabelColor = if (passwordError.isNotEmpty()) errorColor else MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            if (passwordError.isNotEmpty()) {
                Text(passwordError, color = errorColor, fontSize = 12.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
            }
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") }, // Texto del label como en la imagen
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Confirm Password Icon") },
                visualTransformation = PasswordVisualTransformation(),
                isError = confirmError.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (confirmError.isNotEmpty()) errorColor else primaryColor,
                    unfocusedBorderColor = if (confirmError.isNotEmpty()) errorColor else MaterialTheme.colorScheme.outline,
                    focusedLabelColor = if (confirmError.isNotEmpty()) errorColor else primaryColor,
                    unfocusedLabelColor = if (confirmError.isNotEmpty()) errorColor else MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            if (confirmError.isNotEmpty()) {
                Text(confirmError, color = errorColor, fontSize = 12.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
            }

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    // Para que se muestren los errores al intentar registrarse,
                    // llamamos a validate() primero
                    if (validate()) {
                        viewModel.registerUser(name, email, password)
                        navController.navigate(Screen.Login.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                // El botón solo estará habilitado si todos los campos tienen texto,
                // pero la validación completa se hace al hacer clic.
                enabled = name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
            ) {
                Text("Registrarse", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(12.dp))


            // Separador "Or"
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

            // Botón de Facebook
            Button(
                onClick = { /* Lógica de Login con Facebook */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD7D7D7)),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                // Aquí deberías usar un ícono real de Facebook (requiere una librería de íconos personalizada)
                Text("f", color = Color(0xFF1877F2), fontSize = 24.sp, fontWeight = FontWeight.Black)
                Spacer(Modifier.width(16.dp))
                Text("Login with Facebook", color = Color.Black)
            }

            Spacer(Modifier.height(8.dp))

            // Botón de Google
            OutlinedButton(
                onClick = { /* Lógica de Login con Google */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) {
                // Aquí deberías usar un ícono real de Google (requiere una librería de íconos personalizada)
                Text("G", color = Color.Red, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.width(16.dp))
                Text("Login with Google", color = Color.Gray)
            }

            // --- FIN: Botones de Redes Sociales ---

            Spacer(Modifier.height(24.dp))


        }
    }
}