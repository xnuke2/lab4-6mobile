package com.example.lab4_6mobile

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4_6mobile.Entities.Answer
import com.example.lab4_6mobile.Entities.Division
import com.example.lab4_6mobile.Entities.Profession
import com.example.lab4_6mobile.Entities.User
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegistrationActivity: AppCompatActivity() {
    lateinit var professionNames: List<String>
    lateinit var divisionsNames: List<String>
    lateinit var apiService:ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        val buttonRegistrationToLogin = findViewById<Button>(R.id.buttonRegistrationToLogin)
        buttonRegistrationToLogin.setOnClickListener {
            finish()
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:9090/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
        apiService.getProfession()?.enqueue(object : Callback<List<Profession?>?> {
            override fun onResponse(
                call: Call<List<Profession?>?>,
                response: Response<List<Profession?>?>
            ) {
                if (response.isSuccessful) {
                    val professions: List<Profession?>? = response.body()
                    professionNames = professions
                        ?.filterNotNull()              // Убираем null из List<Profession?>
                        ?.map { it.professionName }   // Преобразуем в List<String> (названия профессий)
                        ?: emptyList()                // Если professionsList == null, возвращаем пустой список

                    val adapter: ArrayAdapter<String> = ArrayAdapter(
                        this@RegistrationActivity,
                        android.R.layout.simple_spinner_item,
                        professionNames
                    )

                    // Настраиваем Spinner
                    val spinner = findViewById<Spinner>(R.id.spinnerProfessionRegistration)
                    spinner.adapter = adapter
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                } else {
                    Toast.makeText(
                        this@RegistrationActivity,
                        " Ошибка с запросом. попробуйте позже",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Profession?>?>, t: Throwable) {
                Toast.makeText(
                    this@RegistrationActivity,
                    "Ошибка сети: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        apiService.getDivision()?.enqueue(object : Callback<List<Division?>?> {
            override fun onResponse(
                call: Call<List<Division?>?>,
                response: Response<List<Division?>?>
            ) {
                if (response.isSuccessful) {
                    val divisions: List<Division?>? = response.body()
                    divisionsNames = divisions
                        ?.filterNotNull()              // Убираем null из List<Division?>
                        ?.map { it.divisionName }   // Преобразуем в List<String> (названия подразделений)
                        ?: emptyList()                // Если professionsList == null, возвращаем пустой список

                    val adapter: ArrayAdapter<String> = ArrayAdapter(
                        this@RegistrationActivity,
                        android.R.layout.simple_spinner_item,
                        divisionsNames
                    )

                    // Настраиваем Spinner
                    val spinner = findViewById<Spinner>(R.id.spinnerDivisionRegistration)
                    spinner.adapter = adapter
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                } else {
                    Toast.makeText(
                        this@RegistrationActivity,
                        " Ошибка с запросом. попробуйте позже",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Division?>?>, t: Throwable) {
                Toast.makeText(
                    this@RegistrationActivity,
                    "Ошибка сети: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        val buttonRegistration = findViewById<Button>(R.id.buttonRegistration)
        buttonRegistration.setOnClickListener {
            if(findViewById<EditText>(R.id.editTextTextEmailAddressRegistration).text.toString().isEmpty()||
                findViewById<EditText>(R.id.editTextLastNameRegistration).text.toString().isEmpty()||
                findViewById<EditText>(R.id.editTextFirstNameRegistration).text.toString().isEmpty()||
                findViewById<EditText>(R.id.editTextMiddleNameRegistration).text.toString().isEmpty()||
                findViewById<EditText>(R.id.editTextPhoneRegistration).text.toString().isEmpty()||
                findViewById<EditText>(R.id.editTextTextPasswordRegistration).text.toString().isEmpty()||
                findViewById<Spinner>(R.id.spinnerProfessionRegistration).selectedItemId<0||
                findViewById<Spinner>(R.id.spinnerDivisionRegistration).selectedItemId<0
                ) {
                Toast.makeText(
                    this@RegistrationActivity,
                    "Ошибка: не все поля заполнены",
                    Toast.LENGTH_SHORT
                ).show()
                } else{
                apiService.addUser(User(
                    id = 2,
                    email = findViewById<EditText>(R.id.editTextTextEmailAddressRegistration).text.toString(),
                    lastName = findViewById<EditText>(R.id.editTextLastNameRegistration).text.toString(),
                    firstName = findViewById<EditText>(R.id.editTextFirstNameRegistration).text.toString(),
                    middleName = findViewById<EditText>(R.id.editTextMiddleNameRegistration).text.toString(),
                    phone = findViewById<EditText>(R.id.editTextPhoneRegistration).text.toString(),
                    password = findViewById<EditText>(R.id.editTextTextPasswordRegistration).text.toString(),
                    professionId = findViewById<Spinner>(R.id.spinnerProfessionRegistration).selectedItemId+1,
                    roleId = 1,
                    divisionId = findViewById<Spinner>(R.id.spinnerDivisionRegistration).selectedItemId+1
                ))?.enqueue(object : Callback<Answer?> {
                    override fun onResponse(
                        call: Call<Answer?>,
                        response: Response<Answer?>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@RegistrationActivity,
                                " Успешно",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@RegistrationActivity,
                                " Ошибка с запросом. попробуйте позже",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<Answer?>, t: Throwable) {
                        Toast.makeText(
                            this@RegistrationActivity,
                            "Ошибка сети: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

            }



        }
    }
}