package com.example.latihanpertemuan09.model.response


import com.google.gson.annotations.SerializedName

data class ResponseAddMahasiswa(
    @SerializedName("data")
    val data: String,
    @SerializedName("status")
    val status: String
)