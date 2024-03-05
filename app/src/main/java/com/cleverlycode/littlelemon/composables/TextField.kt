package com.cleverlycode.littlelemon.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextField(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(text = label, fontSize = 12.sp, modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = value,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}