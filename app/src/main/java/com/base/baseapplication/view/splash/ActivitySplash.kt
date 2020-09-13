package com.base.baseapplication.view.splash

import android.content.Intent
import android.os.CountDownTimer
import com.base.baseapplication.R
import com.base.baseapplication.base.BaseActivity
import com.base.baseapplication.data.response.StateData
import com.base.baseapplication.view.main.ActivityMain
import com.base.baseapplication.view.sign_in.ActivitySignIn
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivitySplash : BaseActivity<SplashViewModel>() {

    override val layoutID: Int
        get() = R.layout.activity_splash

    override val viewModel: SplashViewModel by viewModel()
    private var counterTimer: CountDownTimer? = null

    override fun initUI() {

    }


    override fun loadData() {
        counterTimer = object : CountDownTimer(DELAY_TIME, 1000) {
            override fun onFinish() {
                viewModel.checkAuthentication()
                viewModel.splashResponse().observe(this@ActivitySplash, { status ->
                    run {
                        when (status.getStatus()) {
                            StateData.DataStatus.LOADING -> {
                                showLoading()
                            }
                            StateData.DataStatus.SUCCESS -> {
                                hideLoading()
                                if (status.getData()!!) {
                                    Intent(this@ActivitySplash, ActivityMain::class.java).also {
                                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(it)
                                    }
                                } else {
                                    Intent(this@ActivitySplash, ActivitySignIn::class.java).also {
                                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(it)
                                    }
                                }
                                finish()
                            }
                            StateData.DataStatus.ERROR -> {
                                hideLoading()
                                Intent(this@ActivitySplash, ActivitySignIn::class.java).also {
                                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(it)
                                }
                                finish()
                            }
                        }
                    }
                })
            }

            override fun onTick(millisUntilFinished: Long) {
                //called every 1 sec coz countDownInterval = 1000 (1 sec)
            }
        }
        counterTimer?.start()
    }

    override fun onDestroy() {
        counterTimer?.cancel()
        super.onDestroy()
    }

    companion object {
        private const val DELAY_TIME: Long = 2000
    }
}