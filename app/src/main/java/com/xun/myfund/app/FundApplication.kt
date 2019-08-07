package com.xun.myfund.app

import android.app.Application
import android.content.Context

class FundApplication : Application() {

    companion object {
        private var instance: FundApplication? = null

        fun getAppContext(): Context? {
            return instance?.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}