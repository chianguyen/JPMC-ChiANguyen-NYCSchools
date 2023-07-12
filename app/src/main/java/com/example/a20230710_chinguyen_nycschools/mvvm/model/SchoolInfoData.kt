package com.example.a20230710_chinguyen_nycschools.mvvm.model

import com.google.gson.annotations.SerializedName

data class SchoolInfoData (
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("school_name")
    val name: String,
    @SerializedName("num_of_sat_test_takers")
    val testTakerCount: String,
    @SerializedName("sat_critical_reading_avg_score")
    val satReadingAverage: String,
    @SerializedName("sat_math_avg_score")
    val satMathAverage: String,
    @SerializedName("sat_writing_avg_score")
    val satWritingAverage: String
)