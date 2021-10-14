package com.example.map_trips.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.map_trips.R
import com.example.map_trips.core.RetrofitHelper
import com.example.map_trips.data.model.UbicationDetailModel
import com.example.map_trips.data.model.entity.PredictionUbication
import com.example.map_trips.data.model.entity.Ubication
import com.example.map_trips.data.repository.APIService
import com.example.map_trips.view.adapter.UbicationAdapter
import com.example.map_trips.view.adapter.UbicationListener
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), UbicationListener, SearchView.OnQueryTextListener {

    private lateinit var ubicationAdapter: UbicationAdapter
    private var ubications = ArrayList<PredictionUbication>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        svUbications.setOnQueryTextListener(this)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        ubicationAdapter = UbicationAdapter(ubications, this)
        rvUbications.layoutManager = LinearLayoutManager(activity)
        rvUbications.adapter = ubicationAdapter
    }

    override fun onUbicationCliked(ubication: PredictionUbication, position: Int) {
        var place_id = ubication.place_id
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofitGooglePlaceDetailByID().create(APIService::class.java)
                .getDataGooglePlaceDetailByID("json?language=es&key=${RetrofitHelper.TOKEN_GOOGLE_PLACE}&place_id=${place_id}")

            val data = call.body()

            //Para poder salir de esa corrutina utilizaremos
            requireActivity().runOnUiThread {
                if(call.isSuccessful){

                    //show Recyclerview
                    val ubicationDetailModel: UbicationDetailModel? = data ?: null
                    if(ubicationDetailModel != null && ubicationDetailModel.status == "OK"){
                        var token_photo:String = ""
                        if(ubicationDetailModel.result.photos != null){
                             token_photo = ubicationDetailModel.result.photos[0].photo_reference
                        }


                        if (token_photo != null) {
                            Log.d("token .place_id", token_photo)
                        }
                       val ubication:Ubication = Ubication(
                           ubicationDetailModel.result.place_id,
                           ubicationDetailModel.result.formatted_address,
                           token_photo,
                           ubicationDetailModel.result.geometry.location.lat,
                           ubicationDetailModel.result.geometry.location.lng,
                           "","","","",""
                       )

                        val bundle = bundleOf("ubication" to ubication)
                        findNavController().navigate(R.id.ubicationDetailFragment, bundle)

                    }else{
                        Toast.makeText(activity, "Sin información del clima", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    showError()
                }
            }

        }

        Log.d("ubication.place_id", ubication.place_id)
    }

    private fun searchUbicationByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofitGooglePlaceByName().create(APIService::class.java)
                .getDataGooglePlaceByName("json?language=es&types=geocode&key=${RetrofitHelper.TOKEN_GOOGLE_PLACE}&input=${query}")

            val data = call.body()

            //Para poder salir de esa corrutina utilizaremos
            requireActivity().runOnUiThread {
                if(call.isSuccessful){
                    //show Recyclerview
                    val predictions = data?.predictions?: emptyList()


                    if(predictions.isNotEmpty()){
                        ubications.clear()
                        ubications.addAll(predictions)
                        ubicationAdapter.notifyDataSetChanged()
                    }else{
                        Toast.makeText(activity, "Sin resultados encotrados", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    showError()
                }
            }

        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchUbicationByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun showError() {
        Toast.makeText(activity, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}