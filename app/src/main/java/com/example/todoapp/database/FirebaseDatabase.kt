package com.example.todoapp.database

import com.example.todoapp.model.TodoItem

interface FirebaseDatabase {
    suspend fun getAllTodoItems(): Result<List<TodoItem>>
}