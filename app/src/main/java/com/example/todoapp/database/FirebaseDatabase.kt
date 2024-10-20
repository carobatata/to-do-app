package com.example.todoapp.database

interface FirebaseDatabase {
    suspend fun getAllTodoItems(): Result<List<Map<String, Any>>>
}