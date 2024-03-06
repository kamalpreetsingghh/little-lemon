package com.cleverlycode.littlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.room.Room
import com.cleverlycode.littlelemon.AppDatabase
import com.cleverlycode.littlelemon.R
import com.cleverlycode.littlelemon.composables.Chips
import com.cleverlycode.littlelemon.composables.MenuItemsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current

    val database by lazy {
        Room.databaseBuilder(context = context, AppDatabase::class.java, "database").build()
    }

    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    val categories = setOf("all") + databaseMenuItems.map { it.category }.toSet()

    val searchPhrase = remember {
        mutableStateOf("")
    }

    val selectedCategory = remember {
        mutableStateOf("all")
    }

    val menuItems = if(searchPhrase.value.isNotEmpty()) {
        if(selectedCategory.value.lowercase() === "all") {
            databaseMenuItems.filter { it.title.contains(searchPhrase.value, ignoreCase = true) }
        } else {
            databaseMenuItems.filter { it.title.contains(searchPhrase.value, ignoreCase = true) && it.category.lowercase() == selectedCategory.value.lowercase() }
        }
    } else {
        if(selectedCategory.value.lowercase() === "all") {
            databaseMenuItems
        } else {
            databaseMenuItems.filter { it.category.lowercase() == selectedCategory.value.lowercase() }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    alignment = Alignment.Center
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Card(
                        onClick = { navController.navigate(com.cleverlycode.littlelemon.Profile.route) },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(40.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Logo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .aspectRatio(1f)
                        )
                    }
                }
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF485E57))) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Little Lemon", fontSize = 36.sp, color = Color(0xfff4ce15))
                Row(modifier = Modifier.padding(bottom = 6.dp)) {
                    Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                        Text("Chicago", fontSize = 24.sp, color = Color(0xffeaeaea))
                        Text("We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                            color = Color(0xffeaeaea),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(vertical = 6.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.upperpanelimage),
                        contentDescription = "upper panel image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(10))
                    )
                }
                OutlinedTextField(
                    value = searchPhrase.value,
                    onValueChange = { searchPhrase.value = it },
                    placeholder = { Text("Enter search phrase") },
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color(0xffeaeaea)),
                    shape = RoundedCornerShape(15),
                    leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "search icon") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Text(
            text = "ORDER FOR DELIVERY!",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Chips(items = categories.toList(),
            selectedItem = selectedCategory.value,
            onSelectionChanged = {selectedCategoryChip ->
                selectedCategory.value = selectedCategoryChip
                searchPhrase.value = ""
                                 },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .fillMaxWidth()
        )

        MenuItemsList(items = menuItems)
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(navController = rememberNavController())
}