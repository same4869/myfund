package com.xun.myfund.utils

import android.graphics.Color
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

    fun getRandomColor(): Int {
        val random = Random()
        var r = 0
        var g = 0
        var b = 0
        for (i in 0..1) {
            //       result=result*10+random.nextInt(10);
            var temp = random.nextInt(16)
            r = r * 16 + temp
            temp = random.nextInt(16)
            g = g * 16 + temp
            temp = random.nextInt(16)
            b = b * 16 + temp
        }
        return Color.rgb(r, g, b)
    }
}