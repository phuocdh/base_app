package com.base.baseapplication.view.sign_up

import android.content.Intent
import android.view.View
import com.base.baseapplication.R
import com.base.baseapplication.base.BaseActivity
import com.base.baseapplication.data.response.StateData
import com.base.baseapplication.view.main.ActivityMain
import com.base.baseapplication.view.sign_in.ActivitySignIn
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by PhuocDH on 9/12/2020.
 */

class ActivitySignUp : BaseActivity<SignUpViewModel>() {
    override val layoutID: Int
        get() = R.layout.activity_sign_up
    override val viewModel: SignUpViewModel by viewModel()

    override fun initUI() {

    }

    override fun loadData() {
        viewModel.signUpResponse().observe(this@ActivitySignUp, { state ->
            run {
                when (state.getStatus()) {
                    StateData.DataStatus.LOADING -> {
                        showLoading()
                    }
                    StateData.DataStatus.SUCCESS -> {
                        hideLoading()
                        Intent(this@ActivitySignUp, ActivityMain::class.java).also {
                            startActivity(it)
                        }
                        sendBroadcast(Intent(ActivitySignIn::class.java.simpleName))
                        finish()
                    }
                    StateData.DataStatus.ERROR -> {
                        hideLoading()
                        onError(state.getError()?.message!!)
                    }
                }
            }
        })
    }

    fun signUp(view: View) {
        viewModel.signIn(email = edtEmail.text.toString(), password = edtPassword.text.toString())
    }
}