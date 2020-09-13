package com.base.baseapplication.di

import com.base.baseapplication.view.home.HomeViewModel
import com.base.baseapplication.view.main.MainViewModel
import com.base.baseapplication.view.profile.ProfileViewModel
import com.base.baseapplication.view.sign_in.SignInViewModel
import com.base.baseapplication.view.sign_up.SignUpViewModel
import com.base.baseapplication.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}