package com.example.testphinder

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation : Location

    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        initViews()


    }

    private fun initViews() {
        ed_coor.addTextChangedListener(textWatcher)
        btn_gps.setOnClickListener { validateCoordenates() }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.toString().length == 2){
                s?.append(',')
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }

    /*  //trazar ruta en mapa
      btn_gps.setOnClickListener{
              val gmmIntentUri = Uri.parse(
                  "google.navigation:q=" + lastLocation.getLatitud()
                      .toDouble() + "," + actividadSeri.getLongitud().toDouble() + "&mode=d"
              )
              val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
              mapIntent.setPackage("com.google.android.apps.maps")
              startActivity(mapIntent)
          }*/


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMarkerClickListener(this)
        mMap.uiSettings.isZoomControlsEnabled = true
        setUpMap()
    }

    private fun placeMarker(location: LatLng){
        val markerOptions = MarkerOptions().position(location)
        mMap.addMarker(markerOptions)
    }

    override fun onMarkerClick(p0: Marker?) = false

    private fun setUpMap(lat : Double, lon: Double){
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {location ->
            if (location != null){
                lastLocation = location

                val currentLatLng = LatLng(lat, lon)
                placeMarker(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13f))

            }

        }
    }

    private fun setUpMap(){
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {location ->
            if (location != null){
                lastLocation = location

                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarker(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13f))

            }

        }
    }


    private fun validateCoordenates() {
        val co1 : String = "90"
        val co2 : String = "99"

        if (ed_coor.text.length == 5){
            val str = ed_coor.text
            val param : Array<String>  = str.split(',').toTypedArray()
            val edco1 = param[0]
            val edco2 = param[1]
            if (edco1 > co1 && edco2 > co2){
                Toast.makeText(this,"Coordenada inválida", Toast.LENGTH_LONG).show()
            }else{
                setUpMap(edco1.toDouble(), edco2.toDouble())
            }

        }
    }


}
