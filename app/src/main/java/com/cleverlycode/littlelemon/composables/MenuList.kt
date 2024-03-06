package com.cleverlycode.littlelemon.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.cleverlycode.littlelemon.MenuItemRoom

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 20.dp, horizontal = 16.dp)
    ) {
        items(
            items = items,
            itemContent = {menuItem ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Divider(color = Color.Gray, modifier = Modifier.padding(vertical = 6.dp))
                        Row {
                            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                                Text(
                                    menuItem.title,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(vertical = 6.dp)
                                )

                                Text(
                                    text = menuItem.description,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text(
                                    text = "$%.2f".format(menuItem.price),
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(vertical = 6.dp
                                    )
                                )
                            }
                            GlideImage(
                                model = menuItem.image,
                                contentDescription = menuItem.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.aspectRatio(1f).padding(10.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuListPreview() {
    MenuItemsList(listOf(
        MenuItemRoom(
        1,
        "Greek Salad",
        "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
        10.0,
        "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
        "starters"
    ),
        MenuItemRoom(
            1,
            "Greek Salad",
            "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            "starters"
        )
    ))
}

