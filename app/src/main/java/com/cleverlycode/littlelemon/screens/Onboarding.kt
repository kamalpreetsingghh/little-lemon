package com.cleverlycode.littlelemon.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cleverlycode.littlelemon.PreferencesManager
import com.cleverlycode.littlelemon.R
import com.cleverlycode.littlelemon.composables.InputTextField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Onboarding(navController: NavController) {
    val context = LocalContext.current

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val firstName = remember {
        mutableStateOf("")
    }

    val lastName = remember {
        mutableStateOf("")
    }

    val email = remember {
        mutableStateOf("")
    }

    fun register() {
        if(firstName.value.isNotEmpty() && lastName.value.isNotEmpty() && email.value.isNotEmpty()) {
            PreferencesManager(context = context).saveData(key = "firstName", value = firstName.value)
            PreferencesManager(context = context).saveData(key = "lastName", value = lastName.value)
            PreferencesManager(context = context).saveData(key = "email", value = email.value)

            CoroutineScope(Dispatchers.Default).launch {
                snackbarHostState.showSnackbar(
                    message = "Registration successful!",
                    withDismissAction = true,
                    duration = SnackbarDuration.Long
                )
            }
            navController.navigate(com.cleverlycode.littlelemon.Home.route)
        } else {
            CoroutineScope(Dispatchers.Default).launch {
                snackbarHostState.showSnackbar(
                    message = "Registration unsuccessful. Please enter all data.",
                    withDismissAction = true,
                    duration = SnackbarDuration.Long
                )
            }
        }
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF485E57))
                    .padding(40.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Let's get to know you",
                    fontSize = 24.sp,
                    color = Color.White
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

                    InputTextField(
                        value = firstName.value,
                        placeholder = "First Name",
                        onChange = { value -> firstName.value = value}
                    )

                    InputTextField(
                        value = lastName.value,
                        placeholder = "Last Name",
                        onChange = { value -> lastName.value = value}
                    )

                    InputTextField(
                        value = email.value,
                        placeholder = "Email",
                        onChange = { value -> email.value = value}
                    )
                }

                Button(onClick = { register() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff4ce15)),
                    border = BorderStroke(width = 1.dp, color = Color(0xffc6843e))
                ) {
                    Text(text = "Register", fontSize = 16.sp, color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(navController = rememberNavController())
}