package com.xun.myfund.app

import android.app.Application
import android.content.Context
import cn.bmob.v3.Bmob

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

        Bmob.initialize(this, "9da9af97a355d2dfe663c0d9ba898626")
    }
}