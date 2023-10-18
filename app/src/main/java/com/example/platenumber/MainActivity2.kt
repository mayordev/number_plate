package com.example.platenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val vehicleStatus = intent.getSerializableExtra("VEHICLE_STATUS") as VehicleStatusResponse
        val insuranceStatusTextView = findViewById<TextView>(R.id.textViewInsuranceStatus)
        val registrationStatusTextView = findViewById<TextView>(R.id.textViewRegistrationStatus)
        val rwcStatusTextView = findViewById<TextView>(R.id.textViewRWCStatus)
        var registration = vehicleStatus.data.registration.status
        var insurance = vehicleStatus.data.insurance.status
        var rwc = vehicleStatus.data.rwc.status

        registration = when (registration) {
            "EXPIRED" -> {
                "Vehicle Registration status: Expired"
            }
            "VALID" -> {
                "Vehicle Registration status: Valid"
            }
            else -> {
                "Vehicle Registration status Not Found"
            }
        }

        insurance = when (insurance) {
            "EXPIRED" -> {
                "Vehicle Insurance status:Expired"
            }
            "VALID" -> {
                "Vehicle Insurance status: Valid"
            }
            else -> {
                "Vehicle Insurance status Not Found"
            }
        }

        rwc = when (rwc) {
            "EXPIRED" -> {
                "Vehicle RWC status: Expired"
            }
            "VALID" -> {
                "Vehicle RWC status: Valid"
            }
            else -> {
                "Vehicle RWC status Not Found"
            }
        }

        insuranceStatusTextView.text = insurance
        registrationStatusTextView.text = registration
        rwcStatusTextView.text = rwc


    }
}