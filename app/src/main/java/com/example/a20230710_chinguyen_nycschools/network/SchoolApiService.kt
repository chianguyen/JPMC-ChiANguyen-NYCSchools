package com.example.a20230710_chinguyen_nycschools.network

import com.example.a20230710_chinguyen_nycschools.mvvm.model.SchoolData
import com.example.a20230710_chinguyen_nycschools.mvvm.model.SchoolInfoData
import com.example.a20230710_chinguyen_nycschools.util.Constants.INFO_ENDPOINT
import com.example.a20230710_chinguyen_nycschools.util.Constants.SAT_ENDPOINT
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolApiService {

    @GET(INFO_ENDPOINT)
    fun getSchoolList(): Single<List<SchoolData>>

    @GET(SAT_ENDPOINT)
    fun getSchoolInfo(@Query("dbn") dbn: String): Single<SchoolInfoData>

}