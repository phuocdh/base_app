package com.base.baseapplication.view.profile

import com.base.baseapplication.base.BaseViewModel
import com.base.baseapplication.data.model.User
import com.base.baseapplication.data.repositories.IUserRepository
import com.base.baseapplication.data.response.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ProfileViewModel(private val userRepository: IUserRepository) : BaseViewModel() {
    private val userLiveData: StateLiveData<User> = StateLiveData()
    fun userResponse(): StateLiveData<User> {
        return userLiveData
    }

    fun getUser(userId: Int) {
        launchDisposable {
            userRepository.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { userLiveData.postLoading() }
                .subscribe({ response ->
                    if (response != null) {
                        userLiveData.postSuccess(response.user)
                    } else {
                        userLiveData.postError(Throwable("User empty"))
                    }
                }, { e ->
                    if (e.message == null) {
                        userLiveData.postError(Throwable("User empty"))
                    } else {
                        userLiveData.postError(e)
                    }
                })
        }
    }
}