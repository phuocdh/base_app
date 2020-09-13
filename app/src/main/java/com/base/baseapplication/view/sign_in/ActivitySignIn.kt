package com.base.baseapplication.view.sign_in

import android.content.Intent
import android.view.View
import com.base.baseapplication.R
import com.base.baseapplication.base.BaseActivity
import com.base.baseapplication.data.response.StateData
import com.base.baseapplication.view.main.ActivityMain
import com.base.baseapplication.view.sign_up.ActivitySignUp
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by PhuocDH on 9/12/2020.
 */

class ActivitySignIn : BaseActivity<SignInViewModel>() {
    override val layoutID: Int
        get() = R.layout.activity_sign_in
    override val viewModel: SignInViewModel by viewModel()

    override fun initUI() {

    }

    override fun loadData() {
        viewModel.signInResponse().observe(this@ActivitySignIn, { state ->
            run {
                when (state.getStatus()) {
                    StateData.DataStatus.LOADING -> {
                        showLoading()
                    }
                    StateData.DataStatus.SUCCESS -> {
                        hideLoading()
                        Intent(this@ActivitySignIn, ActivityMain::class.java).also {
                            startActivity(it)
                        }
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

    fun signIn(view: View) {
        viewModel.signIn(email = edtEmail.text.toString(), password = edtPassword.text.toString())
    }

    fun signUpView(view: View) {
        Intent(this@ActivitySignIn, ActivitySignUp::class.java).also {
            startActivity(it)
        }
    }
}