package com.example.todoapp.repository

import com.example.todoapp.model.TodoItem

interface TodoRepository {
//    fun doNetworkCall()
    fun getAllTodoItems(): List<TodoItem>
}