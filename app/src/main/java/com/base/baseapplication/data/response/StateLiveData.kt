package com.base.baseapplication.data.response

import androidx.lifecycle.MutableLiveData

/**
 * Created by PhuocDH on 9/12/2020.
 */

class StateLiveData<T> : MutableLiveData<StateData<T?>>() {
    /**
     * Use this to put the Data on a LOADING Status
     */
    fun postLoading() {
        postValue(StateData<T?>().loading())
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     * @param data
     */
    fun postSuccess(data: T?) {
        postValue(StateData<T?>().success(data))
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     * @param throwable the error to be handled
     */
    fun postError(throwable: Throwable) {
        postValue(StateData<T?>().error(throwable))
    }
}