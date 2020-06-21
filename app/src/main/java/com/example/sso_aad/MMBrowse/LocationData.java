package com.example.sso_aad.MMBrowse;

import com.google.android.gms.maps.model.LatLng;

public class LocationData {
    public static  final double[][] LatLong ={
            {16.8409, 96.1735},
            {21.9588, 96.0891},
            {21.1717, 94.8585},
            {20.7888,97.0337},
            {17.3221,96.4663},
            {22.9665,97.7525},
            {21.2827,99.6230},
            {25.3946,97.3841}
    };

    public LatLng getLatLong(int index){
        return (new LatLng(LatLong[index][0], LatLong[index][1]));
    }
}


