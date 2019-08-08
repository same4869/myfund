package com.xun.myfund.bean

import cn.bmob.v3.BmobObject

class FundInfos : BmobObject() {
    var name: String = ""
    var code: String = ""
    var money: Float = 0f
    var buyTime : String = ""
}