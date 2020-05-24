package com.alpyuktug.turkai.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.alpyuktug.turkai.R

import com.alpyuktug.turkai.service.RetrofitClient
import com.rengwuxian.materialedittext.MaterialEditText

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityRegister : AppCompatActivity() {

    var editTextMail: MaterialEditText? = null
    var editTextPassword: MaterialEditText? = null

    var imageViewSignUp: ImageView? = null
    var textViewLogin: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextMail = findViewById(R.id.editTextMail)
        editTextPassword = findViewById(R.id.editTextPassword)
        imageViewSignUp = findViewById(R.id.imageViewSignUp)
        textViewLogin = findViewById(R.id.textViewLogin)

        imageViewSignUp!!.setOnClickListener { Register() }

        textViewLogin!!.setOnClickListener {
            val intent = Intent(applicationContext, ActivityLogin::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun Register() {

        if (!validate()) {
            onRegisterFailed()
            return
        }

        imageViewSignUp!!.isEnabled = false

        android.os.Handler().postDelayed(
            {
                onRegisterSuccess()
            }, 3000)
    }

    fun onRegisterSuccess() {
        imageViewSignUp!!.isEnabled = true

        val email = editTextMail!!.text.toString().trim()
        val password = editTextPassword!!.text.toString().trim()
        val picture = "https://alperenyukselaltug.com/api/TurkAi/picture/woman.png"

        RetrofitClient.instance.Register(email, password, picture)
            .enqueue(object: Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {

                    if(response.body().toString() == "Success")
                    {
                        val intent = Intent(applicationContext, ActivityLogin::class.java)
                        startActivity(intent)
                        finish()
                    }
                    if(response.body().toString() == "Fail")
                    {
                        Toast.makeText(applicationContext, "Email already taken", Toast.LENGTH_LONG).show()
                    }

                }

            })
    }

    fun onRegisterFailed() {
        Toast.makeText(baseContext, "Registered failed", Toast.LENGTH_LONG).show()

        imageViewSignUp!!.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true

        val email = editTextMail!!.text.toString()
        val password = editTextPassword!!.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextMail!!.error = getString(R.string.WrongEmailPattern)
            valid = false
        } else {
            editTextMail!!.error = null
        }

        if (password.isEmpty()) {
            editTextPassword!!.error = getString(R.string.WrongPassword)
            valid = false
        } else {
            editTextPassword!!.error = null
        }

        return valid
    }

}
