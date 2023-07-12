package com.example.a20230710_chinguyen_nycschools.network

import com.example.a20230710_chinguyen_nycschools.di.ISchoolRepository
import com.example.a20230710_chinguyen_nycschools.mvvm.model.SchoolData
import com.example.a20230710_chinguyen_nycschools.mvvm.model.SchoolInfoData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(private val schoolApiService: SchoolApiService): ISchoolRepository {
    override fun getSchoolList(): Single<List<SchoolData>> {
        return schoolApiService.getSchoolList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getSchoolInfo(dbn: String): Single<List<SchoolInfoData>> {
        return schoolApiService.getSchoolInfo(dbn)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}