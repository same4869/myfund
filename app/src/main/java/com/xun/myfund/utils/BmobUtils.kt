package com.xun.myfund.utils

import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.xun.myfund.bean.FundInfos


object BmobUtils {

    //买一个基金
    fun buyAFundByInfo(name: String, code: String, money: Float, buyTime: String) {
        val p2 = FundInfos()
        p2.name = name
        p2.code = code
        p2.money = money
        p2.buyTime = buyTime
        p2.save(object : SaveListener<String>() {
            override fun done(objectId: String?, e: BmobException?) {
                if (e == null) {
                } else {
                    AppUtils.showToast("buyAFundByInfo err : " + e.message)
                    LogUtils.d("kkkkkkkk", "buyAFundByInfo" + e.message)
                }
            }
        })
    }
}