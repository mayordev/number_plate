package com.example.platenumber

import java.io.Serializable


data class VehicleStatusResponse (
    val status: String,
    val data: Data
) : Serializable {

    data class Data (
        val insurance: Insurance,
        val registration: Insurance,
        val rwc: Insurance,
        val bills: List<Any?>
    ) : java.io.Serializable
}

data class Insurance (
    val statusCode: String,
    val status: String,
    val wantedStatus: Boolean,
    val billStatus: String
) : java.io.Serializable
