package com.example.platenumber

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val vehicleStatus = intent.getSerializableExtra("VEHICLE_STATUS") as? VehicleStatusResponse
        val insuranceStatusTextView = findViewById<TextView>(R.id.textViewInsuranceStatus)
        val registrationStatusTextView = findViewById<TextView>(R.id.textViewRegistrationStatus)
        val rwcStatusTextView = findViewById<TextView>(R.id.textViewRWCStatus)
        var registration = vehicleStatus?.data?.registration?.status
        var insurance = vehicleStatus?.data?.insurance?.status
        var rwc = vehicleStatus?.data?.rwc?.status
        val registrationStatus: Drawable?
        val insuranceStatus: Drawable?
        val rwcStatus: Drawable?


        when (registration) {
            "EXPIRED" -> {
                registration = "Vehicle Registration status: Expired"
                registrationStatus = ResourcesCompat.getDrawable(resources, R.drawable.ic_error, null)
            }
            "VALID" -> {
                registration =  "Vehicle Registration status: Valid"
                registrationStatus = ResourcesCompat.getDrawable(resources, R.drawable.ic_check, null)
            }
            else -> {
                registration = "Vehicle Registration status Not Found"
                registrationStatus = ResourcesCompat.getDrawable(resources, R.drawable.ic_warning, null)
            }
        }

        when (insurance) {
            "EXPIRED" -> {
                insurance = "Vehicle Insurance status:Expired"
                insuranceStatus = ResourcesCompat.getDrawable(resources, R.drawable.ic_error, null)
            }
            "VALID" -> {
                insurance =  "Vehicle Insurance status: Valid"
                insuranceStatus = ResourcesCompat.getDrawable(resources, R.drawable.ic_check, null)
            }
            else -> {
                insurance = "Vehicle Insurance status Not Found"
                insuranceStatus = ResourcesCompat.getDrawable(resources, R.drawable.ic_warning, null)
            }
        }

        when (rwc) {
            "EXPIRED" -> {
                rwc = "Vehicle RWC status: Expired"
                rwcStatus = ResourcesCompat.getDrawable(resources, R.drawable.ic_error, null)
            }
            "VALID" -> {
                rwc = "Vehicle RWC status: Valid"
                rwcStatus = ResourcesCompat.getDrawable(resources, R.drawable.ic_check, null)
            }
            else -> {
                rwc = "Vehicle RWC status Not Found"
                rwcStatus = ResourcesCompat.getDrawable(resources, R.drawable.ic_warning, null)
            }
        }

        insuranceStatusTextView.text = insurance
        insuranceStatusTextView.setCompoundDrawablesWithIntrinsicBounds(insuranceStatus, null, null, null)
        registrationStatusTextView.text = registration
        registrationStatusTextView.setCompoundDrawablesWithIntrinsicBounds(registrationStatus, null, null, null)
        rwcStatusTextView.text = rwc
        rwcStatusTextView.setCompoundDrawablesWithIntrinsicBounds(rwcStatus, null, null, null)



    }
}