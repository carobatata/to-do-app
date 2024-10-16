package com.example.todoapp.di

import retrofit2.http.GET

interface MyApi {

    @GET("my/endpoint")
    fun callApi()
}