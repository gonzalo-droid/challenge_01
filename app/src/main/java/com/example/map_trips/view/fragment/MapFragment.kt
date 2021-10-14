package com.example.map_trips.view.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.map_trips.R
import com.example.map_trips.data.model.UbicationList
import com.example.map_trips.data.model.entity.Ubication
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback , GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMyLocationButtonClickListener, // boton de ubicacion
    GoogleMap.OnMyLocationClickListener  // coordenadas
{
    // TODO: Rename and change types of parameters

    private lateinit var map:GoogleMap

    companion object{
        const val REQUEST_CODE_LOCATION=0
    }

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
        createMarkers()

        map.setOnMyLocationButtonClickListener(this)

        enableLocation()
    }

    private fun createMarkers() {
        // lista de las ubicaciones
        var list_ubication = UbicationList.getListUbications();

        // recorre las ubicaciones y asigna los datos para la creacion del market
        for (list in list_ubication){
            val gps = LatLng(list.latitude, list.longitude)
            val market = MarkerOptions().position(gps).title(list.name)
            map.addMarker(market)

        }

        // zoom por defecto a una coordenada
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(
            LatLng(-6.769792136313882,-79.85495851696817)
            , 8f))

        // listener al hacer clic en el market
        map?.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        // Informacion del marker a UbicationDetailFragment
        var name = marker.title

        var list_ubication = UbicationList.getListUbications();
        for (ubication:Ubication in list_ubication){
            if(ubication.name.equals(name.toString())){
//                val bundle = bundleOf(Pair("ubication_id", ubication.id))
                val bundle = bundleOf("ubication" to ubication)
                findNavController().navigate(R.id.ubicationDetailFragment, bundle)
            }
        }

        return true
    }

    private fun enableLocation(){
        // si no esta inicializada return
        if(!::map.isInitialized) return
        // permiso activado
        if(ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            map.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
        )){
            Toast.makeText(requireActivity(), "Activa los permisos de GPS", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty()  && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    map.isMyLocationEnabled = true
                }

            }else{
                Toast.makeText(requireActivity(), "Activa los permisos de GPS", Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }


    override fun onResume() {
        super.onResume()
        if(!::map.isInitialized) return
        if(ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            map.isMyLocationEnabled = false
            Toast.makeText(requireActivity(), "Activa los permisos de GPS", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {

        if (ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = false
            Toast.makeText(requireActivity(), "Activa los permisos de GPS", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        TODO("Not yet implemented")
    }
}