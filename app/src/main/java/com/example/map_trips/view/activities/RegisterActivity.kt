package com.example.map_trips.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.map_trips.R
import com.example.map_trips.data.util.PreferenceApplication.Companion.prefs
import com.example.map_trips.view.fragment.DatePickerFragment
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initUI()
    }

    private fun initUI() {

        tilDate.editText?.setOnClickListener {
            val datePicker = DatePickerFragment{day, month, year ->  onDateSelected(day,month,year)}
            datePicker.show(supportFragmentManager,"date")
        }


        buttonSave.setOnClickListener {
            var name = tilName.editText?.text.toString().trim()
            var lastname = tilLastname.editText?.text.toString().trim()
            var email = tilEmail.editText?.text.toString().trim()
            var phone = tilPhone.editText?.text.toString().trim()
            var date = tilDate.editText?.text.toString().trim()

            if(name.isNotEmpty()){
                prefs.saveName(name)
            }
            if(lastname.isNotEmpty()){
                prefs.saveLastName(lastname)
            }
            if(phone.isNotEmpty()){
                prefs.savePhone(phone)
            }
            if(date.isNotEmpty()){
                prefs.saveDate(date)
            }

            if(email.isNotEmpty()){
                prefs.saveEmail(email)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()

            }else{
                Toast.makeText(this,"Email es obligatorio",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        tilDate.editText?.setText("$day / $month / $year")
    }
}