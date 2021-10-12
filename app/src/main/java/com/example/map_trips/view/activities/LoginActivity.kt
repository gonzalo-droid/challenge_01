package com.example.map_trips.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.map_trips.R
import com.example.map_trips.data.util.PreferenceApplication.Companion.prefs

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
            finish()
        }
    }

    private fun initUI() {
        btnLogin.setOnClickListener {
            var username = tilUsername.editText?.text.toString().trim()

            if(username.isNotEmpty() && username.equals(prefs.getEmail())){
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"Email incorrecto",Toast.LENGTH_SHORT).show()
            }

        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }


}