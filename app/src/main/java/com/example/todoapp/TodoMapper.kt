package com.example.todoapp

import com.example.todoapp.model.TodoItem
import com.example.todoapp.model.TodoItemUI

object TodoMapper {
    fun convertToTodoItemUI(todoItem: TodoItem) : TodoItemUI {
        return TodoItemUI(
            name = todoItem.name.orEmpty(),
            date = todoItem.date?.let { transformTimestampToDateString(it) } ?: "",
            isDone = todoItem.isDone
        )
    }
}