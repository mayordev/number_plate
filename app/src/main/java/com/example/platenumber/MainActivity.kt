package com.example.platenumber

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.platenumber.RetrofitHelper.createRetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private  lateinit var edit : EditText
    private var plateNumber: String = "";
    private lateinit var submit: Button
    private lateinit var vehicleStatusApiService: VehicleStatusApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edit  = findViewById(R.id.editPlate)
        submit = findViewById(R.id.button)


         vehicleStatusApiService = createRetrofitInstance().create(VehicleStatusApiService::class.java)
        // launching a new coroutine


        submit.setOnClickListener {
            verify()
        }

    }

     fun verify (){
         plateNumber = edit.text.toString()
        if (plateNumber.length != 10){
            Toast.makeText(this, "Please enter a valid Plate Number", Toast.LENGTH_LONG).show()
        }
        else{
            val call: Call<VehicleStatusResponse> = vehicleStatusApiService.getVehicleStatus(plateNumber)
            call.enqueue(object : Callback<VehicleStatusResponse> {
                override fun onResponse(
                    call: Call<VehicleStatusResponse>,
                    response: Response<VehicleStatusResponse>
                ) {
                    val intent = Intent(this@MainActivity, MainActivity2::class.java).also {
                        it.putExtra("EXTRA_MESSAGE", plateNumber)
                        startActivity(it)
                    }
                }

                override fun onFailure(call: Call<VehicleStatusResponse>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })




        }

    }
}