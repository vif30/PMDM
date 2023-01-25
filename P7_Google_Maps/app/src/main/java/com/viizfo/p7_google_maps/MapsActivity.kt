package com.viizfo.p7_google_maps

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.viizfo.p7_google_maps.Location.Companion.readLocations
import com.viizfo.p7_google_maps.databinding.ActivityMapsBinding

const val REQUEST_PERSMISSION_CODE = 1000

class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var mLatitude = 0.0
    private var mLongitude = 0.0
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create()
        locationRequest !!. priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        locationRequest !!. interval = 16000 // 16seconds
        locationRequest !!. fastestInterval = 8000 // 8seconds
        locationRequest.smallestDisplacement = 0f


        locationCallback = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult){
                super.onLocationResult(locationResult)
                for(location in locationResult.locations){
                    if(location != null){
                        mLatitude = location.latitude
                        mLongitude = location.longitude
                        Toast.makeText(this@MapsActivity, "Lat:" + mLatitude + "Lon:" +
                                mLongitude, Toast.LENGTH_LONG) .show();
                    }
                }
            }
        }
        var titles:String = ""
        val locations = Location.readLocations(this)
        for (location:Location in locations){
            titles += "- " + location.title + " "
        }
        Toast.makeText(this, "Titles: $titles", Toast.LENGTH_SHORT).show()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val torrent = LatLng(40.0, 0.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(torrent).title("Meridian Park").snippet("Inhabitants: 180000")) // Add extra information below the title)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        /*mMap.setOnCameraIdleListener {
            Toast.makeText(this,
                "Zoom changed to: " + mMap.cameraPosition.zoom,
                Toast.LENGTH_SHORT).show()
        }*/
        /*mMap.setOnMapClickListener {latlng ->
            Toast.makeText(this,
                "Map clicked on lat: ${latlng.latitude}, long: ${latlng.longitude}",
                Toast.LENGTH_SHORT).show()
        }*/
        /*val SYDNEY = LatLng(-33.88,151.21)
        val MOUNTAIN_VIEW = LatLng(37.4, -122.1)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 15f)) //Starts with zoom of 15 zoom
        mMap.animateCamera(CameraUpdateFactory.zoomIn())
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10f), 5000, null) //5 seconds
        val cameraPosition = CameraPosition.Builder()
            .target(MOUNTAIN_VIEW) //final destination
            .zoom(17f) //new final zoom
            .bearing(90f) //camera orientation to the east
            .tilt(30f) //camera to 30 degrees
            .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))*/
        mMap.setMapStyle (MapStyleOptions.loadRawResourceStyle (this, R.raw.style_json))
    }
    private fun requestLocations() {
        if (ActivityCompat.checkSelfPermission(this@MapsActivity,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@MapsActivity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this@MapsActivity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_PERSMISSION_CODE)
        } else {
            //mFusedLocationClient!!.requestLocationUpdates(locationRequest!!,locationCallback!!, Looper.getMainLooper())
        }
    }

    private fun removeLocations() {
        mFusedLocationClient!!.removeLocationUpdates(locationCallback!!)
    }
}
