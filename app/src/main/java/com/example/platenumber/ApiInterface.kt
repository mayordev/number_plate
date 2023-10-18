package com.example.platenumber

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleStatusApiService {
    @GET("index.php")
     fun getVehicleStatus(@Query("number_plate") numberPlate: String): Call<VehicleStatusResponse>
}
