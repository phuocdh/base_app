package com.base.baseapplication

import android.app.Activity
import android.app.Application
import com.base.baseapplication.di.appModule
import com.base.baseapplication.di.networkModule
import com.base.baseapplication.di.repositoryModule
import com.base.baseapplication.di.viewModelModule
import com.base.baseapplication.utils.GlideApp
import com.base.baseapplication.utils.LogUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    private var currentClass: Class<*>? = null

    override fun onCreate() {
        super.onCreate()
        sInstance = this

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(listOf(appModule, networkModule, viewModelModule, repositoryModule))
        }
    }

    override fun onLowMemory() {
        GlideApp.get(this).onLowMemory()
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        GlideApp.get(this).onTrimMemory(level)
        super.onTrimMemory(level)
    }

    fun setCurrentClass(clazz: Class<out Activity>) {
        currentClass = clazz
        LogUtils.d("CurrentClass: ", clazz.javaClass.simpleName)
    }

    fun getCurrentClass(): Class<*>? {
        return currentClass
    }

    companion object {
        private const val TAG = "MainApplication"
        lateinit var sInstance: MainApplication
    }
}