package com.example.todoapp.repository

import com.example.todoapp.model.TodoItem

interface TodoRepository {
    suspend fun getAllTodoItems(): Result<List<TodoItem>>
    suspend fun createTodoItem(todoItem: TodoItem): Result<Unit>
}