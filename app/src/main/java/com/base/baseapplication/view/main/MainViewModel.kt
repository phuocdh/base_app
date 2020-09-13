package com.base.baseapplication.view.main

import com.base.baseapplication.base.BaseViewModel
import com.base.baseapplication.data.repositories.IAuthRepository


class MainViewModel(private val authRepository: IAuthRepository) : BaseViewModel() {

    fun logout() {
        authRepository.logout()
    }
}