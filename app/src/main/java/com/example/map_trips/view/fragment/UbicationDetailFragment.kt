package com.example.map_trips.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.map_trips.R
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.map_trips.core.RetrofitHelper
import com.example.map_trips.data.model.UbicationList
import com.example.map_trips.data.model.entity.Ubication
import com.example.map_trips.data.repository.APIService
import kotlinx.android.synthetic.main.fragment_ubication_detail.*

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UbicationDetailFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close,)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener {
            dismiss()
        }


        val name = arguments?.getSerializable("name")

        var list_ubication = UbicationList.getListUbications();

        for (ubication in list_ubication){
            if(ubication.name.equals(name.toString())){

                searchByGPS(ubication)

                Glide.with(ivImage.context)
                    .load(getResources().getIdentifier(ubication.photo, "drawable", requireContext().packageName))
                    .into(ivImage)
                tvTitle.text= ubication.name
                tvTemperature.text= ubication.capital
                tvHumidity.text= ubication.number_people

                break
            }


        }




    }

    private fun searchByGPS(ubication: Ubication) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofit().create(APIService::class.java)
                .getDataUbication("weather?appid=${RetrofitHelper.TOKEN}&lang=es&units=metric&lat=${ubication.latitude}&lon=${ubication.longitude}")

            val data = call.body()

            requireActivity().runOnUiThread{
                if(call.isSuccessful){
                    //show Recyclerview
                    val wind_speed = data?.wind?.speed

                    Log.d("clouads", wind_speed.toString())

                }else{
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }


}


