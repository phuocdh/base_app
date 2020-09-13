package com.base.baseapplication.widget

import android.content.Context
import es.dmoral.toasty.Toasty
import java.lang.ref.WeakReference

class DialogManagerImpl(ctx: Context?) : DialogManager {

    private var context: WeakReference<Context?>? = null
    private var dialogProgress: DialogProgress? = null

    init {
        context = WeakReference(ctx).apply {
            dialogProgress = DialogProgress(this.get()!!)
        }
    }

    override fun showLoading() {
        dialogProgress?.show()
    }

    override fun hideLoading() {
        dialogProgress?.dismiss()
    }

    override fun showError(error: String) {
        Toasty.error(context?.get()!!, error).show()
    }

    companion object {
        const val TAG = "DialogManagerImpl"
    }
}