package com.example.lab4_6mobile

import com.example.lab4_6mobile.Entities.Answer
import com.example.lab4_6mobile.Entities.Division
import com.example.lab4_6mobile.Entities.Profession
import com.example.lab4_6mobile.Entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("profession")
    fun getProfession(): Call<List<Profession?>?>?
    @GET("division")
    fun getDivision(): Call<List<Division?>?>?
    @GET("profile")
    fun getUsers(): Call<List<User?>?>?
    @POST("auth/register")
    fun addUser(@Body user: User):Call<Answer?>
}