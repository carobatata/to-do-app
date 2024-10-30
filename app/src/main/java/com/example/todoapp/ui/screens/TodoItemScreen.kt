package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todoapp.model.TodoItemUI
import com.example.todoapp.ui.theme.DarkGreen
import com.example.todoapp.ui.theme.LightGreen
import com.example.todoapp.ui.theme.White

@Composable
fun TodoItemScreen(item: TodoItemUI) {
    Card(
        modifier = Modifier.padding(vertical = 10.dp),
        colors = CardColors(
            containerColor = DarkGreen,
            contentColor = White,
            LightGreen,
            White
        )
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.DateRange, null)
                Spacer(modifier = Modifier.padding(2.dp))
                Text(item.date)
            }
        }
    }
}