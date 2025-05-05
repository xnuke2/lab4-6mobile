package com.example.lab4_6mobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_6mobile.Entities.User
import com.example.lab4_6mobile.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserListActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var b: ActivityMainBinding
    private var ItemsList: List<User> = emptyList()
    lateinit var apiService:ApiService
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_users)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:9090/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val btnRegistration = findViewById<Button>(R.id.buttonToRegistration)
        btnRegistration.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        apiService = retrofit.create(ApiService::class.java)
        apiService.getUsers()?.enqueue(object : Callback<List<User?>?> {
            override fun onResponse(
                call: Call<List<User?>?>,
                response: Response<List<User?>?>
            ) {
                if (response.isSuccessful) {
                    val professions: List<User?>? = response.body()
                    ItemsList = professions
                        ?.filterNotNull()              //
                        ?: emptyList<User>()
                    setUpAdapter()// Если professionsList == null, возвращаем пустой список


                } else {
                    Toast.makeText(
                        this@UserListActivity,
                        " Ошибка с запросом. попробуйте позже",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<User?>?>, t: Throwable) {
                Toast.makeText(
                    this@UserListActivity,
                    "Ошибка сети: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })



    }
    private var allowRefresh = false

    public override fun onResume() {
        super.onResume()
        //Initialize();
        if (allowRefresh) {
            allowRefresh = false

            apiService.getUsers()?.enqueue(object : Callback<List<User?>?> {
                override fun onResponse(
                    call: Call<List<User?>?>,
                    response: Response<List<User?>?>
                ) {
                    if (response.isSuccessful) {
                        val professions: List<User?>? = response.body()
                        ItemsList = professions
                            ?.filterNotNull()              //
                            ?: emptyList<User>()
                        setUpAdapter()// Если professionsList == null, возвращаем пустой список


                    } else {
                        Toast.makeText(
                            this@UserListActivity,
                            " Ошибка с запросом. попробуйте позже",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<User?>?>, t: Throwable) {
                    Toast.makeText(
                        this@UserListActivity,
                        "Ошибка сети: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    public override fun onPause() {
        super.onPause()
        if (!allowRefresh) allowRefresh = true
    }
    private fun setUpAdapter() {

        adapter = UserAdapter(this,ItemsList)
        val recyclerView=findViewById<RecyclerView>(R.id.AllUsers)
        recyclerView.adapter =adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}