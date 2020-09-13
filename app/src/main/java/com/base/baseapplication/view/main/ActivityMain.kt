package com.base.baseapplication.view.main

import android.content.Intent
import android.view.View
import com.base.baseapplication.R
import com.base.baseapplication.base.BaseActivity
import com.base.baseapplication.view.home.FragmentHome
import com.base.baseapplication.view.sign_in.ActivitySignIn
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by PhuocDH on 9/12/2020.
 */

class ActivityMain : BaseActivity<MainViewModel>() {
    override val layoutID: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun initUI() {
    }

    override fun loadData() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, FragmentHome())
            .commit()
    }

    fun logout(view: View) {
        viewModel.logout()
        Intent(this@ActivityMain, ActivitySignIn::class.java).also {
            startActivity(it)
        }
        finish()
    }
}