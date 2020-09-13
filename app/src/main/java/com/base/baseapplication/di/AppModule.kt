package com.base.baseapplication.di

import android.app.Application
import android.content.res.Resources
import com.base.baseapplication.data.firebase.FirebaseSource
import com.base.baseapplication.data.middleware.BooleanAdapter
import com.base.baseapplication.data.middleware.DoubleAdapter
import com.base.baseapplication.data.middleware.IntegerAdapter
import com.base.baseapplication.data.sharedprf.SharedPrefsApi
import com.base.baseapplication.data.sharedprf.SharedPrefsImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { provideResources(androidApplication()) }
    single { provideSharedPrefsApi(androidApplication()) }
    single { provideGson() }
    single { provideFirebaseAuth() }
    single { provideFirebaseSource(get()) }
}

fun provideResources(app: Application): Resources {
    return app.resources
}

fun provideSharedPrefsApi(app: Application): SharedPrefsApi {
    return SharedPrefsImpl(app)
}

fun provideGson(): Gson {
    val booleanAdapter = BooleanAdapter()
    val integerAdapter = IntegerAdapter()
    val doubleAdapter = DoubleAdapter()
    return GsonBuilder()
        .registerTypeAdapter(Boolean::class.java, booleanAdapter)
        .registerTypeAdapter(Int::class.java, integerAdapter)
        .registerTypeAdapter(Double::class.java, doubleAdapter)
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}

fun provideFirebaseAuth(): FirebaseAuth {
    return FirebaseAuth.getInstance()
}

fun provideFirebaseSource(firebaseAuth: FirebaseAuth): FirebaseSource {
    return FirebaseSource(firebaseAuth)
}