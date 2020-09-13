package com.base.baseapplication.utils.extension

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.base.baseapplication.R
import com.base.baseapplication.utils.GlideApp
import com.base.baseapplication.utils.LogUtils
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun View.show(isShow: Boolean = true) {
    visibility = if (isShow) View.VISIBLE else View.INVISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.gone(isShow: Boolean = true) {
    visibility = if (isShow) View.VISIBLE else View.GONE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun Context.showToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

@SuppressLint("SetJavaScriptEnabled")
fun WebView.loadWebViewUrl(url: String?, progressBar: ProgressBar?) {
    if (url.isNullOrEmpty()) return
    if (progressBar == null) {
        webViewClient = WebViewClient()
    } else {
        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }
    }
    with(settings) {
        javaScriptEnabled = true
        scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
    }
    loadUrl(url)
    LogUtils.d("loadWebViewUrl", url)
}

fun ImageView.loadImage(url: String?) {
    url.notNull {
        GlideApp.with(this.context).load(it)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .dontTransform()
            .centerInside()
            .placeholder(R.drawable.ic_user)
            .into(this)
    }
}

fun ImageView.loadCircleImage(url: String?) {
    url.notNull {
        GlideApp.with(this.context).load(it)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .dontTransform()
            .centerInside()
            .circleCrop()
            .placeholder(R.drawable.ic_user)
            .into(this)
    }
}

fun ImageView.loadImage(bitmap: Bitmap) {
    bitmap.notNull {
        GlideApp.with(this.context).load(bitmap)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .dontTransform()
            .placeholder(R.drawable.ic_user)
            .into(this)
    }
}

fun ImageView.loadImage(uri: Uri?) {
    uri.notNull {
        GlideApp.with(this.context).load(it)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .dontTransform()
            .placeholder(R.drawable.ic_user)
            .into(this)
    }
}

fun View.OnClickListener.listenToViews(vararg views: View) {
    views.forEach { it.setOnClickListener(this) }
}

fun View.setIsSelected(isSelect: Boolean = true) {
    this.isSelected = isSelect
}

@SuppressLint("ClickableViewAccessibility")
fun View.setOnVeryLongClickListener(timeDelay: Long = 3000, listener: () -> Unit) {
    setOnTouchListener(object : View.OnTouchListener {

        private val handler = Handler(Looper.getMainLooper())

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            if (event?.action == MotionEvent.ACTION_DOWN) {
                handler.postDelayed({
                    listener.invoke()
                }, timeDelay)
            } else if (event?.action == MotionEvent.ACTION_UP) {
                handler.removeCallbacksAndMessages(null)
            }
            return true
        }
    })
}