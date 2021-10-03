package com.example.map_trips.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.map_trips.R
import com.example.map_trips.model.PreferenceApplication
import com.example.map_trips.model.PreferenceApplication.Companion.prefs
import com.example.map_trips.view.activities.LoginActivity
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        initUI()


    }

    private fun setData() {

        if(prefs.getName().isNotEmpty()){
            tilUpdateName.editText?.setText(prefs.getName())
        }

        if(prefs.getLastName().isNotEmpty()){
            tilUpdateLastname.editText?.setText(prefs.getLastName())
        }
        if(prefs.getEmail().isNotEmpty()){
            tilUpdateEmail.editText?.setText(prefs.getEmail())
        }
        if(prefs.getPhone().isNotEmpty()){
            tilUpdatePhone.editText?.setText(prefs.getPhone())

        }

    }

    private fun initUI(){
        updateData()
        btnLogout.setOnClickListener {
            prefs.resetData()
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    private fun updateData() {
        btnUpdate.setOnClickListener {
            var name = tilUpdateName.editText?.text.toString().trim()
            var lastname = tilUpdateLastname.editText?.text.toString().trim()
            var email = tilUpdateEmail.editText?.text.toString().trim()
            var phone = tilUpdatePhone.editText?.text.toString().trim()

            if(name.isNotEmpty()){
                prefs.saveName(name)
            }
            if(lastname.isNotEmpty()){
                prefs.saveLastName(lastname)
            }
            if(phone.isNotEmpty()){
                prefs.savePhone(phone)
            }

            if(email.isNotEmpty()){
                prefs.saveEmail(email)
                setData()
                Toast.makeText(context,"Datos Actualizados",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Email es obligatorio",Toast.LENGTH_SHORT).show()
            }

        }
    }




}