package com.example.latihanpertemuan09.model.response


import com.example.latihanpertemuan09.model.request.DetailDataMahasiswa
import com.google.gson.annotations.SerializedName

data class ResponseDetailMahasiswa(
    @SerializedName("data")
    val data: DetailDataMahasiswa,
    @SerializedName("status")
    val status: String
)