package com.xun.myfund

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ScrollView
import android.widget.Toast
import com.xun.myfund.bean.Item
import com.xun.myfund.bean.TradeHistoryBean
import com.xun.myfund.config.Constants.Companion.BASE_URL
import com.xun.myfund.net.RetrofitClient
import com.xun.myfund.utils.AppUtils
import com.xun.myfund.utils.applySchedulers
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AlertDialog
import com.xun.myfund.utils.BmobUtils


class MainActivity : AppCompatActivity() {
    private var lastItem: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtn1.setOnClickListener {
            RetrofitClient.getApiServiceWithBaseUrl(BASE_URL).requestTradeHistory(20, 1).applySchedulers().subscribe({
                setupInfo(it)
            }, {
                Toast.makeText(applicationContext, "err -> " + it.message, Toast.LENGTH_SHORT).show()
            })
        }

        mBtn2.setOnClickListener {
            appendToScreen("--******----------我的总览----------******--")
            appendToScreen("--******--------我的总览结束--------******--")
        }

        mBtn3.setOnClickListener {
            if (lastItem == null) {
                AppUtils.showToast("跟投失败~请先获取最新数据")
                return@setOnClickListener
            }
            showADialog()
        }
    }

    private fun showADialog() {
        val builder = AlertDialog.Builder(this)  //建立一个对象
        builder.setTitle("提示")    //标题
        builder.setMessage("请问确定要跟投吗？")
        //正面的按钮
        builder.setPositiveButton(
            "是的"
        ) { _, _ ->
            appendToScreen("--******----------正在跟投----------******--")
            for (i in 0 until lastItem?.trading_elements?.size!!) {
                BmobUtils.buyAFundByInfo(
                    lastItem!!.trading_elements[i].fd_name,
                    lastItem!!.trading_elements[i].fd_code,
                    100f,
                    "2019-08-07"
                )
            }
            appendToScreen("--******----------正在完毕----------******--")
        }
        //反面的按钮
        builder.setNegativeButton(
            "取消"
        ) { _, _ ->

        }
        builder.show()
    }

    private fun setupInfo(it: TradeHistoryBean) {
        appendToScreen("--******----------概况总览----------******--")
        appendToScreen("最近20期投资金额：")
        var totleMoney = 0f
        for (i in it.data.items) {
            appendToScreen(i.buy_amount.toString(), ",")
            totleMoney += i.buy_amount.toFloat()
        }
        appendToScreen("平均每期（周）投资：" + totleMoney / 20)
        appendToScreen("平均每月投资：" + totleMoney * 52 / 240)
        appendToScreen("--------------------------------------")
        appendToScreen("最近一期推荐基金：")
        var curTotleMoney = 0f
        lastItem = it.data.items[0]
        it.data.items[0].apply {
            appendToScreen("日期->" + AppUtils.timeStamp2Date(trade_date))
            for (j in trading_elements) {
                appendToScreen("********************************")
                appendToScreen("名称->" + j.fd_name)
                appendToScreen("代码->" + j.fd_code)
                appendToScreen("金额->" + j.money)
                appendToScreen("占比->" + j.portion)
                appendToScreen("********************************")
                curTotleMoney += j.money
            }
        }
        appendToScreen("本期总投资金额：$curTotleMoney")
        appendToScreen("--******--------概况总览结束--------******--")

    }

    private fun appendToScreen(str: String, split: String = "\n") {
        mTipsTv.text = mTipsTv.text.toString() + split + str
        Handler().post {
            mScrollView.fullScroll(ScrollView.FOCUS_DOWN)
        }
    }
}
