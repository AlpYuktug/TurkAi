package com.alpyuktug.turkai.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alpyuktug.turkai.R
import com.alpyuktug.turkai.service.RetrofitClient
import com.rengwuxian.materialedittext.MaterialEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.R.id
import com.alpyuktug.turkai.util.CustomSharedPrefences

class ActivityLogin : AppCompatActivity() {

    var editTextMail: MaterialEditText? = null
    var editTextPassword: MaterialEditText? = null

    var imageViewLogin: ImageView? = null
    var textViewRegister: TextView? = null
    var textViewLogin: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        editTextMail = findViewById(R.id.editTextMail)
        editTextPassword = findViewById(R.id.editTextPassword)
        imageViewLogin = findViewById(R.id.imageViewLogin)
        textViewRegister = findViewById(R.id.textViewRegister)
        textViewLogin = findViewById(R.id.textViewLogin)

        imageViewLogin!!.setOnClickListener { Login() }

        textViewRegister!!.setOnClickListener {
            val intent = Intent(applicationContext, ActivityRegister::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun Login() {

        if (!validate()) {
            onLoginFailed()
            return
        }

        imageViewLogin!!.isEnabled = false

        android.os.Handler().postDelayed(
            {
                onLoginSuccess()
            }, 3000)
    }


    fun onLoginSuccess() {
        imageViewLogin!!.isEnabled = true

        val email = editTextMail!!.text.toString().trim()
        val password = editTextPassword!!.text.toString().trim()

        val customSharedPrefences = CustomSharedPrefences(applicationContext)
        customSharedPrefences.saveEmail(email)

        RetrofitClient.instance.Login(email, password)
            .enqueue(object: Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {

                    if(response.body().toString() == "Success")
                    {

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    if(response.body().toString() == "Fail")
                    {
                        Toast.makeText(applicationContext, getString(R.string.WrongInformation), Toast.LENGTH_LONG).show()
                    }

                }

            })

    }


    fun onLoginFailed() {
        Toast.makeText(baseContext, getString(R.string.MustBlank), Toast.LENGTH_LONG).show()

        imageViewLogin!!.isEnabled = true
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