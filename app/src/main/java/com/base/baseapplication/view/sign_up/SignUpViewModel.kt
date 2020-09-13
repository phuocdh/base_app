package com.base.baseapplication.view.sign_up

import com.base.baseapplication.base.BaseViewModel
import com.base.baseapplication.data.repositories.IAuthRepository
import com.base.baseapplication.data.response.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SignUpViewModel(private val authRepository: IAuthRepository) : BaseViewModel() {
    private val signUpLiveData: StateLiveData<Boolean> = StateLiveData()

    fun signUpResponse(): StateLiveData<Boolean> {
        return signUpLiveData
    }

    fun signIn(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            signUpLiveData.postError(Throwable("Please input all values"))
            return
        }
        launchDisposable {
            authRepository.signUp(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { signUpLiveData.postLoading() }
                .subscribe({
                    signUpLiveData.postSuccess(true)
                }, { e ->
                    signUpLiveData.postError(e)
                })
        }
    }
}
