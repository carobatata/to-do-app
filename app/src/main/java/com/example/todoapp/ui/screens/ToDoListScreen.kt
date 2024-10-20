package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.TodoViewModel
import com.example.todoapp.ui.model.TodoItemUiState
import org.koin.androidx.compose.getViewModel

@Composable
fun ToDoListScreen() {
    val viewModel = getViewModel<TodoViewModel>()
    val todoItems = viewModel.todoItemsUiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.getAllTodoItems()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        when (todoItems.value) {
            is TodoItemUiState.Success -> {
                (todoItems.value as TodoItemUiState.Success).todoItemUI.forEach { item ->
                    TodoItemScreen(item)
                }
            }
            TodoItemUiState.Error -> {}
            TodoItemUiState.Loading -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoListScreenPreview() {
    ToDoListScreen()
}