package com.base.baseapplication.view.profile

import com.base.baseapplication.R
import com.base.baseapplication.base.BaseActivity
import com.base.baseapplication.data.response.StateData
import com.base.baseapplication.utils.Constants.USER_ID
import com.base.baseapplication.utils.extension.loadCircleImage
import kotlinx.android.synthetic.main.activity_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by PhuocDH on 9/12/2020.
 */

class ActivityProfile : BaseActivity<ProfileViewModel>() {
    override val layoutID: Int
        get() = R.layout.activity_profile
    override val viewModel: ProfileViewModel by viewModel()
    private var userId: Int = -1

    override fun initUI() {
        intent?.let {
            userId = it.getIntExtra(USER_ID, -1)

        }
    }

    override fun loadData() {
        viewModel.getUser(userId)
        viewModel.userResponse().observe(this, { state ->
            run {
                when (state.getStatus()) {
                    StateData.DataStatus.LOADING -> {
                        showLoading()
                    }
                    StateData.DataStatus.SUCCESS -> {
                        hideLoading()
                        val user = state.getData()
                        user?.let {
                            imvUser.loadCircleImage(user.avatar)
                            txtFullName.text = "${user.firstName} ${user.lastName}"
                            txtEmail.text = user.email
                        }
                    }
                    StateData.DataStatus.ERROR -> {
                        hideLoading()
                        onError(state.getError()?.message!!)
                    }
                }
            }
        })
    }

}