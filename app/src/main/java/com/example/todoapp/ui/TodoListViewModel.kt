package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.TodoMapper
import com.example.todoapp.repository.TodoRepository
import com.example.todoapp.ui.model.TodoItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoListViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    private var _todoItemsUiState = MutableStateFlow<TodoItemUiState>(TodoItemUiState.Loading)
    val todoItemsUiState: StateFlow<TodoItemUiState> = _todoItemsUiState

    fun getAllTodoItems() {
        _todoItemsUiState.value = TodoItemUiState.Loading
        viewModelScope.launch {
            val todosResult = todoRepository.getAllTodoItems()

            _todoItemsUiState.update {
                when {
                    todosResult.isSuccess -> {
                        val todos = todosResult.getOrNull() ?: emptyList()
                        val todosUi = todos.map { todo ->
                            TodoMapper.convertToTodoItemUI(todo)
                        }
                        TodoItemUiState.Success(todosUi)
                    }
                    else -> {
                        TodoItemUiState.Error
                    }
                }
            }
        }
    }
}