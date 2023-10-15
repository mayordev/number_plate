package com.example.platenumber


data class VehicleStatusResponse (
    val status: String,
    val data: Data
)

data class Data (
    val insurance: Insurance,
    val registration: Insurance,
    val rwc: Insurance,
    val bills: List<Any?>
)

data class Insurance (
    val statusCode: String,
    val status: String,
    val wantedStatus: Boolean,
    val billStatus: String
)