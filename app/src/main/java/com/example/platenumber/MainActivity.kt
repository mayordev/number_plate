package com.example.platenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val submit: Button = findViewById(R.id.button)

        val vehicleStatusApiService = RetrofitHelper.getInstance().create(VehicleStatusApiService::class.java)

        submit.setOnClickListener {
            verify()

        }

    }

    fun verify (){
        val edit : EditText = findViewById(R.id.editPlate)
        val plateNumber = edit.text.toString()
        if (plateNumber.length != 10){
            Toast.makeText(this, "Please enter a valid Plate Number", Toast.LENGTH_LONG).show()
        }
        else{
            val intent = Intent(this, MainActivity2::class.java).also{
                it.putExtra("EXTRA_MESSAGE", plateNumber)
                startActivity(it)
            }
        }

    }
}