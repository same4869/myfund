package com.xun.myfund

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.xun.myfund.config.Constants.Companion.BASE_URL
import com.xun.myfund.net.RetrofitClient
import com.xun.myfund.utils.LogUtils
import com.xun.myfund.utils.applySchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitClient.getApiServiceWithBaseUrl(BASE_URL).requestTradeHistory(20, 1).applySchedulers().subscribe({
            mTipsTv.text = it.toString()
        }, {
            LogUtils.d("kkkkkkkk", "err -> " + it.message)
        })
    }
}
