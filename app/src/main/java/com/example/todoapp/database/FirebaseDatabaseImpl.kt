package com.example.todoapp.database

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDatabaseImpl(private val dataBase: FirebaseFirestore) : FirebaseDatabase {
    override suspend fun getAllTodoItems(): Result<List<Map<String, Any>>> {
        return try {
            val data = dataBase.collection("todolist")
                .get()
                .await()
                .documents.mapNotNull { doc ->
                    doc.data // This returns a Map<String, Any>
                }
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
