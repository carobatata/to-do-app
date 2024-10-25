package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.model.TodoItem
import com.example.todoapp.repository.TodoRepository
import com.example.todoapp.ui.model.TodoItemUiState
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateTodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    private val _createTodoStatus = MutableStateFlow<TodoItemUiState>(TodoItemUiState.Idle)
    val createTodoStatus: StateFlow<TodoItemUiState> get() = _createTodoStatus

    fun createTodoItem(name: String) {
        val todoItem = TodoItem(
            name = name,
            date = Timestamp.now()
        )

        viewModelScope.launch {
            _createTodoStatus.value = TodoItemUiState.Loading

            val response = todoRepository.createTodoItem(todoItem)

            _createTodoStatus.value = when {
                response.isSuccess -> TodoItemUiState.SuccessWithoutData
                else -> TodoItemUiState.Error
            }
        }
    }
}