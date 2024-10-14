package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val _todoItems = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoItems: StateFlow<List<TodoItem>> = _todoItems

    init {
        getAllTodoItems()
    }

    fun getAllTodoItems() {
        viewModelScope.launch {
            _todoItems.value = listOf(
                TodoItem("Book esterlization for Canela", "14 oct 2024"),
                TodoItem("Hire Canela insurance", "10 oct 2024"),
                TodoItem("Hire new internet provider", "08 sept 2024"),
            )
        }
    }
}