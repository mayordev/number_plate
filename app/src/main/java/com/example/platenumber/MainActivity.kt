package com.example.platenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.platenumber.RetrofitHelper.createRetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var edit: EditText // Declare EditText as lateinit
    lateinit var submit: Button // Declare Button as lateinit
    lateinit var vehicleStatusApiService: VehicleStatusApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit = findViewById(R.id.editPlate)
        submit = findViewById(R.id.button)
        vehicleStatusApiService =
            RetrofitHelper.createRetrofitInstance().create(VehicleStatusApiService::class.java)

        submit.setOnClickListener {
            verify()
        }
    }

    fun verify() {
        val plateNumber = edit.text.toString()
        if (plateNumber.isEmpty()){
            Toast.makeText(
                this@MainActivity,
                "Enter plate number",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val result = vehicleStatusApiService.getVehicleStatus(plateNumber)
                if (result.isSuccessful) {
                    val vehicleStatus = result.body()
                    if (vehicleStatus != null) {
                        if (result.isSuccessful) {
                            val intent = Intent(this@MainActivity, MainActivity2::class.java)
                            intent.putExtra("VEHICLE_STATUS", vehicleStatus)
                            startActivity(intent)
                        }else{
                            runOnUiThread {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Empty response from the API.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(
                                this@MainActivity,
                                "Empty response from the API.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "Failed to fetch vehicle status.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "Network error. Please check your connection.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
