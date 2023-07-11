package com.example.a20230710_chinguyen_nycschools.mvvm.model

import com.google.gson.annotations.SerializedName

data class SchoolData(
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("school_name")
    val name: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("location")
    val address: String,
    @SerializedName("phone_number")
    val phone: String,
    @SerializedName("school_email")
    val email: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("total_students")
    val totalStudent: String,
    @SerializedName("school_sports")
    val sports: String,
    @SerializedName("latitude")
    val lat: String,
    @SerializedName("longitude")
    val longitude: String
)