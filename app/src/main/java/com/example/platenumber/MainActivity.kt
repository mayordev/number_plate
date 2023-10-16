package com.example.platenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.util.Log
import com.example.platenumber.RetrofitHelper.createRetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {
    val edit : EditText = findViewById(R.id.editPlate)
    val plateNumber = edit.text.toString()
    val submit: Button = findViewById(R.id.button)
    lateinit var vehicleStatusApiService: VehicleStatusApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         vehicleStatusApiService = createRetrofitInstance().create(VehicleStatusApiService::class.java)
        // launching a new coroutine


        submit.setOnClickListener {
            verify()
        }

    }

     fun verify (){
        if (plateNumber.length != 10){
            Toast.makeText(this, "Please enter a valid Plate Number", Toast.LENGTH_LONG).show()
        }
        else{
            GlobalScope.launch {
                val result = vehicleStatusApiService.getVehicleStatus(plateNumber).execute()
                if (result != null) {
                    val intent = Intent(this@MainActivity, MainActivity2::class.java).also {
                        it.putExtra("EXTRA_MESSAGE", plateNumber)
                        startActivity(it)
                    }
                    // Checking the results
                    Log.d("mayor: ", result.body().toString())
                }
            }



        }

    }
}