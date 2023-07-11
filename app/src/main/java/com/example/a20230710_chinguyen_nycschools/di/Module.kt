package com.example.a20230710_chinguyen_nycschools.di

import com.example.a20230710_chinguyen_nycschools.network.Repository
import com.example.a20230710_chinguyen_nycschools.network.SchoolApiService
import com.example.a20230710_chinguyen_nycschools.util.Constants.SCHOOL_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SCHOOL_BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: SchoolApiService): ISchoolRepository {
        return Repository(apiService)
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): SchoolApiService {
        return retrofit.create(SchoolApiService::class.java)
    }

}