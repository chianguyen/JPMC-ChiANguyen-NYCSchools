package com.example.a20230710_chinguyen_nycschools.di

import com.example.a20230710_chinguyen_nycschools.mvvm.model.SchoolData
import com.example.a20230710_chinguyen_nycschools.mvvm.model.SchoolInfoData
import io.reactivex.Single

interface ISchoolRepository {
    fun getSchoolList(): Single<List<SchoolData>>
    fun getSchoolInfo(dbn: String): Single<SchoolInfoData>
}