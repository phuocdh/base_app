package com.base.baseapplication.view.sign_in

import com.base.baseapplication.base.BaseViewModel
import com.base.baseapplication.data.repositories.IAuthRepository
import com.base.baseapplication.data.response.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SignInViewModel(private val authRepository: IAuthRepository) : BaseViewModel() {
    private val signInLiveData: StateLiveData<Boolean> = StateLiveData()

    fun signInResponse(): StateLiveData<Boolean> {
        return signInLiveData
    }

    fun signIn(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            signInLiveData.postError(Throwable("Please input all values"))
            return
        }
        launchDisposable {
            authRepository.signIn(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { signInLiveData.postLoading() }
                .subscribe({
                    signInLiveData.postSuccess(true)
                }, { e ->
                    signInLiveData.postError(e)
                })
        }
    }
}
