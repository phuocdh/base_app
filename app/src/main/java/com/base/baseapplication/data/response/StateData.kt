package com.base.baseapplication.data.response

/**
 * Created by PhuocDH on 9/12/2020.
 */

class StateData<T> {
    private var status: DataStatus
    private var data: T?
    private var error: Throwable?

    fun loading(): StateData<T> {
        status = DataStatus.LOADING
        data = null
        error = null
        return this
    }

    fun success(data: T): StateData<T> {
        status = DataStatus.SUCCESS
        this.data = data
        error = null
        return this
    }

    fun error(error: Throwable): StateData<T> {
        status = DataStatus.ERROR
        data = null
        this.error = error
        return this
    }

    fun getStatus(): DataStatus {
        return status
    }

    fun getData(): T? {
        return data
    }

    fun getError(): Throwable? {
        return error
    }

    enum class DataStatus {
        LOADING, SUCCESS, ERROR
    }

    init {
        status = DataStatus.LOADING
        data = null
        error = null
    }
}