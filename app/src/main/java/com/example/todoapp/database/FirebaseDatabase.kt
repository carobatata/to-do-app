package com.example.todoapp.database

import com.example.todoapp.model.TodoItem

interface FirebaseDatabase {
    suspend fun getAllTodoItems(): Result<List<Map<String, Any>>>
    suspend fun createTodoItem(todoItem: TodoItem): Result<Unit>
}