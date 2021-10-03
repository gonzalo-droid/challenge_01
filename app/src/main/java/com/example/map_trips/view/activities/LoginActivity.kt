package com.example.map_trips.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.map_trips.R
import com.example.map_trips.model.PreferenceApplication.Companion.prefs

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkSession()
        initUI()

    }

    private fun checkSession() {
        if(prefs.getEmail().isNotEmpty()){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun initUI() {
        btnLogin.setOnClickListener {
            var username = tilUsername.editText?.text.toString().trim()

            if(username.isNotEmpty() && username.equals(prefs.getEmail())){
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                Toast.makeText(this,"Email incorrecto",Toast.LENGTH_SHORT).show()
            }

        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }


}