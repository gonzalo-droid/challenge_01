package com.example.map_trips.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.map_trips.R
import com.example.map_trips.data.util.PreferenceApplication.Companion.prefs
import com.example.map_trips.view.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

        statusConnection()

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
        if(prefs.getDate().isNotEmpty()){
            tilUpdateDate.editText?.setText(prefs.getDate())

        }

    }

    private fun initUI(){

        toolbarProfile.inflateMenu(R.menu.menu_toolbar)

        tilUpdateDate.editText?.setOnClickListener {
            val datePicker = DatePickerFragment{day, month, year ->  onDateSelected(day,month,year)}
            activity?.let { it1 -> datePicker.show(it1?.supportFragmentManager, "datePicker") }
        }

        updateData()
        btnLogout.setOnClickListener {
            prefs.resetData()
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        tilUpdateDate.editText?.setText("$day / $month / $year")
    }

    private fun updateData() {
        btnUpdate.setOnClickListener {
            var name = tilUpdateName.editText?.text.toString().trim()
            var lastname = tilUpdateLastname.editText?.text.toString().trim()
            var email = tilUpdateEmail.editText?.text.toString().trim()
            var phone = tilUpdatePhone.editText?.text.toString().trim()
            var date = tilUpdateDate.editText?.text.toString().trim()

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
                setData()
                Toast.makeText(context,"Datos Actualizados",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Email es obligatorio",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.btn_connection -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun statusConnection() {
        toolbarProfile.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.btn_connection -> {
                    Toast.makeText(activity, prefs.getConnection(), Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }


}