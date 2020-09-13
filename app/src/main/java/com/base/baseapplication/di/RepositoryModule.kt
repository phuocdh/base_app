package com.base.baseapplication.di

import android.app.Application
import com.base.baseapplication.data.firebase.FirebaseSource
import com.base.baseapplication.data.repositories.*
import com.base.baseapplication.data.service.AppApi
import com.base.baseapplication.data.sharedprf.SharedPrefsImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { provideTokenRepository(get()) }
    single { provideAuthRepository(get()) }
    single { provideUserRepository(get()) }
}

fun provideTokenRepository(app: Application): ITokenRepository {
    return TokenRepositoryImpl(
        SharedPrefsImpl(app)
    )
}

fun provideAuthRepository(firebaseSource: FirebaseSource): IAuthRepository {
    return AuthRepositoryImpl(firebaseSource)
}

fun provideUserRepository(appApi: AppApi): IUserRepository {
    return UserRepositoryImpl(appApi)
}
