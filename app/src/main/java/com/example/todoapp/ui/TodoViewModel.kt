package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.TodoMapper
import com.example.todoapp.model.TodoItemUI
import com.example.todoapp.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    private val _todoItems = MutableStateFlow<List<TodoItemUI>>(emptyList())
    val todoItems: StateFlow<List<TodoItemUI>> = _todoItems

    init {
        getAllTodoItems()
    }

    private fun getAllTodoItems() {
        viewModelScope.launch {
            val todos = todoRepository.getAllTodoItems()

            val todosUi = todos.map { todo ->
                TodoMapper.convertToTodoItemUI(todo)
            }

            _todoItems.value = todosUi
        }
    }
}