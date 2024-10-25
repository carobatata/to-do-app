package com.example.todoapp.database

import com.example.todoapp.model.TodoItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDatabaseImpl(private val dataBase: FirebaseFirestore) : FirebaseDatabase {
    override suspend fun getAllTodoItems(): Result<List<Map<String, Any>>> {
        return try {
            val response = dataBase.collection("todolist")
                .get()
                .await()
                .documents
                .mapNotNull { it.data }
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createTodoItem(todoItem: TodoItem): Result<Unit> {
        return try {
            dataBase.collection("todolist").add(todoItem).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}