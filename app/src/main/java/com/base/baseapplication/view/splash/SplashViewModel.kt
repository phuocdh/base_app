package com.base.baseapplication.view.splash

import com.base.baseapplication.base.BaseViewModel
import com.base.baseapplication.data.response.StateLiveData
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel(private val firebaseAuth: FirebaseAuth) : BaseViewModel() {
    private val splashLiveData: StateLiveData<Boolean> = StateLiveData()
    fun splashResponse(): StateLiveData<Boolean> {
        return splashLiveData
    }

    fun checkAuthentication() {
        splashLiveData.postLoading()
        if (firebaseAuth.currentUser == null) {
            splashLiveData.postError(Throwable("User not exits"))
        } else {
            splashLiveData.postSuccess(true)
        }
    }
}