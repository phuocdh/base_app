package com.base.baseapplication.view.home

import com.base.baseapplication.base.BaseViewModel
import com.base.baseapplication.data.model.User
import com.base.baseapplication.data.repositories.IUserRepository
import com.base.baseapplication.data.response.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val userRepository: IUserRepository) : BaseViewModel() {
    private val usersLiveData: StateLiveData<MutableList<User>> = StateLiveData()
    fun usersResponse(): StateLiveData<MutableList<User>> {
        return usersLiveData
    }

    fun getListUser() {
        launchDisposable {
            userRepository.getListUser(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { usersLiveData.postLoading() }
                .subscribe({ response ->
                    if (response != null) {
                        usersLiveData.postSuccess(response.users)
                    } else {
                        usersLiveData.postError(Throwable("List empty"))
                    }
                }, { e ->
                    if (e.message == null) {
                        usersLiveData.postError(Throwable("List empty"))
                    } else {
                        usersLiveData.postError(e)
                    }
                })
        }
    }
}