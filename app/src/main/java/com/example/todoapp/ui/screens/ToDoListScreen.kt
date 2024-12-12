package com.example.todoapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.ui.TodoListViewModel
import com.example.todoapp.ui.model.TodoItemUiState
import org.koin.androidx.compose.getViewModel

@Composable
fun ToDoListScreen() {
    val viewModel = getViewModel<TodoListViewModel>()
    val todoItems = viewModel.todoItemsUiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.getAllTodoItems()
    }

    when (todoItems.value) {
        is TodoItemUiState.Success -> {
            LazyColumn {
                items((todoItems.value as TodoItemUiState.Success).todoItemUI) { item ->
                    TodoItemScreen(item)
                }
            }
        }

        TodoItemUiState.Error -> {}
        TodoItemUiState.Loading -> {
            AppSpinner()
        }

        TodoItemUiState.Idle -> {}
        TodoItemUiState.SuccessWithoutData -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoListScreenPreview() {
    ToDoListScreen()
}