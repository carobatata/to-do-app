package com.example.todoapp.database

import com.example.todoapp.model.TodoItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDatabaseImpl(private val dataBase: FirebaseFirestore) : FirebaseDatabase {
    override suspend fun getAllTodoItems(): Result<List<TodoItem>> {
        return try {
            val todoItems = dataBase.collection("todolist")
                .get()
                .await()
                .mapNotNull { snapshot ->
                    snapshot.toObject(
                        TodoItem::class.java
                    )
                }
            Result.success(todoItems)
        } catch (e: Exception) {
            return Result.failure(Exception(e.cause?.message))
        }
    }
}