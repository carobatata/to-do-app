package com.example.todoapp.ui.model

import com.example.todoapp.model.TodoItemUI

sealed class TodoItemUiState {
    data object Error : TodoItemUiState()
    data object Loading : TodoItemUiState()
    data class Success(val todoItemUI: List<TodoItemUI>) : TodoItemUiState()
}