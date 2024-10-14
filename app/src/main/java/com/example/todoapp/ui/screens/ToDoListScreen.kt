package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.TodoViewModel

@Composable
fun ToDoListScreen() {
    val viewModel = viewModel<TodoViewModel>()
    val todoItems = viewModel.todoItems.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp)) {
        todoItems.value.forEach { item ->
            Card(modifier = Modifier.padding(vertical = 10.dp)) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.title,
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
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoListScreenPreview() {
    ToDoListScreen()
}