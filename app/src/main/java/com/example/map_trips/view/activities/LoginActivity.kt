package com.example.map_trips.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.map_trips.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val intentHome = Intent(this, HomeActivity::class.java)
            startActivity(intentHome)
        }

        val btnRegiter = findViewById<Button>(R.id.btnRegister)

        btnRegiter.setOnClickListener {
            val intentRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentRegister)
        }
    }


}