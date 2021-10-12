package com.example.map_trips.data.model

import com.example.map_trips.data.model.entity.Ubication

class UbicationList {
    companion object {
        fun getListUbications(): ArrayList<Ubication> {
            val list_ubication = ArrayList<Ubication>()
            list_ubication.add(
                Ubication("1","Lambayeque","Chiclayo","1 197,3 habitantes",
                "chiclayo",-6.769792136313882,-79.85495851696817)
            )
            list_ubication.add(
                Ubication("2","La Libertad","Trujillo","1 778,1 habitantes",
                "trujillo",-8.102478792274614,-79.03329118502873)
            );
            list_ubication.add(
                Ubication("3","Cajamarca","Cajamarca","1 341,0 habitantes",
                "cajamarca",-7.1639424580468845, -78.48238840347038)
            );
            list_ubication.add(
                Ubication("4","Piura","Piura","1 856,8 habitantes",
                "piura",-5.197632571282029, -80.62108687465448)
            );
            list_ubication.add(
                Ubication("5","Lima","Lima","9 485,4 habitantes",
                "lima",-12.021125885814234, -77.04413896325967)
            );
            return list_ubication
        }


    }
}