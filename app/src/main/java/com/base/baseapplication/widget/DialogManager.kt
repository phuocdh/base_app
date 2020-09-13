package com.base.baseapplication.widget

interface DialogManager {
    fun showLoading()
    fun hideLoading()
    fun showError(error: String)
}