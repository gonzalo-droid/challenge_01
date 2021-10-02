package com.example.map_trips.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.map_trips.R
import com.example.map_trips.model.Ubication
import com.example.map_trips.model.UbicationList
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback , GoogleMap.OnMarkerClickListener {
    // TODO: Rename and change types of parameters

    private lateinit var map:GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // se lleva cuando el mapa se ha creado
        map = googleMap

        var list_ubication = UbicationList.getListUbications();


        for (list in list_ubication){
            val gps = LatLng(list.latitude, list.longitude)
            val market = MarkerOptions().position(gps).title(list.name)
            map.addMarker(market)

        }

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(
            LatLng(-6.769792136313882,-79.85495851696817)
            , 8f))


        map?.setOnMarkerClickListener(this)

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        var name = marker.title
        val bundle = bundleOf(Pair("name",name))
        findNavController().navigate(R.id.ubicationDetailFragment, bundle)
        return true
    }
}