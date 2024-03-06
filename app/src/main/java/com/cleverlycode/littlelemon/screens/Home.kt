package com.cleverlycode.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.cleverlycode.littlelemon.AppDatabase
import com.cleverlycode.littlelemon.Profile
import com.cleverlycode.littlelemon.R
import com.cleverlycode.littlelemon.composables.MenuItemsList

@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current

    val database by lazy {
        Room.databaseBuilder(context = context, AppDatabase::class.java, "database").build()
    }

    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.height(40.dp)
            )

            Button(onClick = { navController.navigate(Profile.route) },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff4ce15)),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                )
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF485E57))) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Little Lemon", fontSize = 36.sp, color = Color(0xfff4ce15))
                Row() {
                    Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                        Text("Chicago", fontSize = 24.sp, color = Color.White)
                        Text("We are a family owned Mediterranean restaurant, focused on traditional recipes served with a\n" +
                                "modern twist.", color = Color.White)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.height(50.dp)
                    )
                }
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Enter Search Phrase") },
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.White),
                    shape = RoundedCornerShape(15)
                )
            }
        }

        MenuItemsList(items = databaseMenuItems)

    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(navController = rememberNavController())
}