package com.example.lab4_6mobile.Entities

data class User (
    val id:Long,
    val email: String,
    var lastName: String,
    var firstName: String,
    var middleName: String,
     var phone: String,
    var password: String,
    var professionId:Long,
    var roleId:Long,
    var divisionId:Long
)
data class Result (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<User>
)
