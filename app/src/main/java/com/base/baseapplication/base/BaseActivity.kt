package com.base.baseapplication.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.base.baseapplication.widget.DialogManager
import com.base.baseapplication.widget.DialogManagerImpl

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), BaseView {
    private lateinit var dialogManager: DialogManager
    protected abstract val layoutID: Int
    protected abstract val viewModel: VM
    protected abstract fun initUI()
    protected abstract fun loadData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutID)
        dialogManager = DialogManagerImpl(this)
        initUI()
        loadData()
        registerReceiver(broadCastReceiverFinish, IntentFilter(this.javaClass.simpleName))
    }

    override fun showLoading() {
        dialogManager.showLoading()
    }

    override fun hideLoading() {
        dialogManager.hideLoading()
    }

    override fun onError(error: String) {
        dialogManager.showError(error)
        hideLoading()
    }

    fun onBack(view: View) {
        onBackPressed()
    }

    fun onFinishView(view: View) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadCastReceiverFinish)
    }

    private val broadCastReceiverFinish = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            finish()
        }
    }
}