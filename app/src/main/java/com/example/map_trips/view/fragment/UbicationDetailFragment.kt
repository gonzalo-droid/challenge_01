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


        val ubication = arguments?.getSerializable("ubication") as Ubication
        searchByGPS(ubication)

    }

    private fun searchByGPS(ubication: Ubication) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofit().create(APIService::class.java)
                .getDataUbication("weather?appid=${RetrofitHelper.TOKEN}&lang=es&units=metric&lat=${ubication.latitude}&lon=${ubication.longitude}")

            val data = call.body()

            requireActivity().runOnUiThread{
                if(call.isSuccessful){
                    //show Recyclerview

                    val humidity = data?.main?.humidity.toString()
                    val temperature = data?.main?.temp.toString()
                    val clouds = data?.clouds?.all.toString()
                    val wind = data?.wind?.speed.toString()

                    val icon = data?.weather?.get(0)?.icon.toString()



                    if(ubication.photo != null && ubication.photo != "" ){
                        Log.d("img-place", ubication.photo)

                        // img google place
                        Glide.with(ivImage.context)
                            .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=${RetrofitHelper.TOKEN_GOOGLE_PLACE}&photo_reference=${ubication.photo}")
                            .into(ivImage)
                    }else{
                        Glide.with(ivImage.context)
                            .load(getResources().getIdentifier("github_logo", "drawable", requireContext().packageName))
                            .preload(500, 300)
                    }

                    Log.d("img-place", "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=${RetrofitHelper.TOKEN_GOOGLE_PLACE}&photo_reference=${ubication.photo}")
                    //icono de  weather
                    Glide.with(ivIconWeather.context)
                        .load("https://openweathermap.org/img/wn/$icon@2x.png")
                        .into(ivIconWeather)

                    tvTitle.text= ubication.name
                    tvWind.text= wind + " m/s"
                    tvCloud.text= clouds + " %"
                    tvHumidity.text= humidity + " %"
                    tvTemperature.text= temperature + " C°"



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


