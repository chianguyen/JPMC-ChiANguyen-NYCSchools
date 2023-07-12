package com.example.a20230710_chinguyen_nycschools.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a20230710_chinguyen_nycschools.di.ISchoolRepository
import com.example.a20230710_chinguyen_nycschools.mvvm.model.SchoolData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(private val repository: ISchoolRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    val schoolList = MutableLiveData<List<SchoolData>>()

    init {
        getSchoolList()
    }

    fun getSchoolList() {
        disposable.add(repository.getSchoolList()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { schoolList.postValue(it) },
                Throwable::printStackTrace
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}