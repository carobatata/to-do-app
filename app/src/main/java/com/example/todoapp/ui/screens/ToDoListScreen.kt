package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.TodoViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ToDoListScreen() {
    val viewModel = getViewModel<TodoViewModel>()
    val todoItems = viewModel.todoItems.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp)) {
        todoItems.value.forEach { item ->
            TodoItemScreen(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoListScreenPreview() {
    ToDoListScreen()
}