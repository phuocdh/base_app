package com.base.baseapplication.base

/**
 * @author PhuocDH.
 */

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(error: String)
}
