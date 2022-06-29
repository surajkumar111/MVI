package com.example.mvi.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SpecifyJobSchedulerIdRange")
class ServiceJob : JobService() {

    var locationManager: LocationManager? = null
    var locationListener = LocationListener {
       // Log.i("mylocation", it.longitude.toString() + " " + it.latitude)

    }

    override fun onCreate() {
        super.onCreate()
        Log.i("mylocation", "job created")
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            Log.i("mylocation", "request permissoion")
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            2000L,
            10F, locationListener
        )
    }

    override fun onStartJob(params: JobParameters?): Boolean {

        Log.i("mylocation", "job started")
        CoroutineScope(Dispatchers.Main).launch {
            for (i in 1..100) {
                delay(2000)
                Log.i("mylocation", i.toString())
            }
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.i("mylocation", "job stoped")
        return true
    }

}