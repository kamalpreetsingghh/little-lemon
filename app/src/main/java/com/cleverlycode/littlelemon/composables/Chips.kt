package com.cleverlycode.littlelemon.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun Chips(
    items: List<String>,
    selectedItem: String,
    onSelectionChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(items) { item ->
            Chip(
                text = item,
                modifier = Modifier,
                isSelected = item == selectedItem,
                onSelectionChanged = onSelectionChanged
            )
        }
    }
}

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {}
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = if (isSelected) Color(0xFF485E57) else Color(0xffeaeaea),
        contentColor = if (isSelected) Color(0xffeaeaea) else Color(0xFF485E57)
    ) {
        Row(
            modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = { onSelectionChanged(text) }
            )
        ) {
            Text(
                text = text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}