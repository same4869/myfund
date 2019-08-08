package com.xun.myfund.utils

import android.widget.Toast
import com.xun.myfund.app.FundApplication
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {
    fun timeStamp2Date(time: Long): String {
        var format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format)
        return sdf.format(Date(time))
    }

    fun showToast(msg: String) {
        Toast.makeText(FundApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show()
    }
}