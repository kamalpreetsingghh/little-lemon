package com.cleverlycode.littlelemon.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cleverlycode.littlelemon.Onboarding
import com.cleverlycode.littlelemon.PreferencesManager
import com.cleverlycode.littlelemon.R
import com.cleverlycode.littlelemon.composables.TextField

@Composable
fun Profile(navController: NavController) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val preferencesManager = PreferencesManager(LocalContext.current)

    val firstName = preferencesManager.getData("firstName", "")
    val lastName = preferencesManager.getData("lastName", "")
    val email = preferencesManager.getData("email", "")

    fun logout() {
        preferencesManager.clearData()
        navController.navigate(Onboarding.route)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(contentPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(50.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Personal Information",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 36.dp)
                    )

                    TextField(
                        value = firstName,
                        label = "First Name",
                    )

                    TextField(
                        value = lastName,
                        label = "Last Name",
                    )

                    TextField(
                        value = email,
                        label = "Email",
                    )
                }

                Button(onClick = { logout() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff4ce15)),
                    border = BorderStroke(width = 1.dp, color = Color(0xffc6843e))
                ) {
                    Text(text = "Log out", fontSize = 16.sp, color = Color.Black)
                }
            }
        }
    }
}