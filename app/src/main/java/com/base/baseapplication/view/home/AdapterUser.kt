package com.base.baseapplication.view.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.baseapplication.R
import com.base.baseapplication.base.BaseRecyclerViewAdapter
import com.base.baseapplication.data.model.User
import com.base.baseapplication.utils.extension.inflate
import com.base.baseapplication.utils.extension.listen
import com.base.baseapplication.utils.extension.loadCircleImage
import kotlinx.android.synthetic.main.item_user.view.*

class AdapterUser(
    context: Context,
    val onItemClick: (user: User, position: Int) -> Unit
) :
    BaseRecyclerViewAdapter<User, RecyclerView.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.inflate(R.layout.item_user)
        return ItemViewHolder(itemView).listen { position, _ ->
            getItem(position)?.let { department ->
                onItemClick(department, position)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bindData(getItem(position)!!)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(user: User) {
            view.imvUser.loadCircleImage(user.avatar)
            view.txtFullName.text = "${user.firstName} ${user.lastName}"
            view.txtEmail.text = user.email
        }
    }


}