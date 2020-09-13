package com.base.baseapplication.view.home

import android.content.Intent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.baseapplication.R
import com.base.baseapplication.base.BaseFragment
import com.base.baseapplication.data.model.User
import com.base.baseapplication.data.response.StateData
import com.base.baseapplication.databinding.FragmentHomeBinding
import com.base.baseapplication.utils.Constants.USER_ID
import com.base.baseapplication.view.profile.ActivityProfile
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentHome : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val layoutID: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModel()
    private lateinit var adapterUser: AdapterUser
    override fun initUI() {
        adapterUser = AdapterUser(context!!, ::onItemClick)
        rcvUser.layoutManager = LinearLayoutManager(context)
        rcvUser.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        rcvUser.adapter = adapterUser
    }

    override fun loadData() {
        viewModel.getListUser()
        viewModel.usersResponse().observe(this, { state ->
            run {
                when (state.getStatus()) {
                    StateData.DataStatus.LOADING -> {
                        showLoading()
                    }
                    StateData.DataStatus.SUCCESS -> {
                        hideLoading()
                        adapterUser.replaceData(state.getData())
                    }
                    StateData.DataStatus.ERROR -> {
                        hideLoading()
                        onError(state.getError()?.message!!)
                    }
                }
            }
        })
    }

    private fun onItemClick(user: User, position: Int) {
        Intent(context, ActivityProfile::class.java).also { intent ->
            intent.putExtra(USER_ID, user.id)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "FragmentCamera"
    }
}