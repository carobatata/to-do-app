package com.example.todoapp.di

import com.example.todoapp.MainActivity
import org.koin.dsl.module

//with this it will only live in the life of the activity
val activityModule = module {
    scope<MainActivity> {
        scoped { "hello" }
    }
}