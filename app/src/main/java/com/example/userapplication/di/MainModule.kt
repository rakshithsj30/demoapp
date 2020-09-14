package com.example.userapplication.di

import com.example.userapplication.repositories.DataRepository
import com.example.userapplication.requests.ImageListApi
import com.example.userapplication.utils.Constants
import com.example.userapplication.viewmodels.ImageListViewModel
import org.koin.dsl.module.module
import org.koin.android.viewmodel.ext.koin.viewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dependency Injection using Koin
 */

val mainModule = module {

    single { DataRepository(get()); }

    single { createWebService() }

    viewModel { ImageListViewModel(get()) }
}


fun createWebService(): ImageListApi {
    val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()

    return retrofit.create(ImageListApi::class.java)
}
