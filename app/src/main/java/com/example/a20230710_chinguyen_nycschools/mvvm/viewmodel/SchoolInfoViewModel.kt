package com.example.a20230710_chinguyen_nycschools.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a20230710_chinguyen_nycschools.di.ISchoolRepository
import com.example.a20230710_chinguyen_nycschools.mvvm.model.SchoolInfoData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SchoolInfoViewModel @Inject constructor(private val repository: ISchoolRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    val schoolInfo = MutableLiveData<List<SchoolInfoData>>()

    fun getSchoolInfo(dbn: String) {
        disposable.add(repository.getSchoolInfo(dbn)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { schoolInfo.postValue(it) },
                { Log.d("SCHOOLED", "getSchoolInfo: ${it.message.toString()}") }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}