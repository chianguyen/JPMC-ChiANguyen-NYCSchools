package com.example.a20230710_chinguyen_nycschools.mvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20230710_chinguyen_nycschools.di.ISchoolRepository
import com.example.a20230710_chinguyen_nycschools.mvvm.model.SchoolData
import com.example.a20230710_chinguyen_nycschools.network.Repository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class SchoolListViewModelTest {
    private val repo = mockk<Repository>(relaxed = true)
    private val subject = SchoolListViewModel(repo)

    @JvmField
    @Rule
    val rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setup(){
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown(){
        clearAllMocks()
    }

    @Test
    fun getSchoolList_emptyResponse_returnsEmpty(){
        val schoolListResponse = listOf<SchoolData>()

        every { repo.getSchoolList() } returns Single.just(schoolListResponse)

        subject.getSchoolList()

        verify { repo.getSchoolList() }
        assertTrue(subject.schoolList.value == schoolListResponse)

    }

}