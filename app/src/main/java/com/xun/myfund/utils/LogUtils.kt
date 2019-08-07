package com.xun.myfund.utils

import android.util.Log

/**
 *  author: xun.wang on 2019/4/2
 *  log输出工具类，可以支持输出方法和行数
 **/

object LogUtils {
    var mLogEnable = true

    private var mClassname: String? = null
    private var mMethods: ArrayList<String>? = null

    init {
        mClassname = LogUtils::class.java.getName()
        mMethods = ArrayList()

        val ms = LogUtils::class.java.getDeclaredMethods()
        for (m in ms) {
            mMethods!!.add(m.getName())
        }
    }

    fun init(logEnable: Boolean) {
        mLogEnable = logEnable
    }

    fun d(tag: String, msg: String) {
        if (mLogEnable) {
            Log.d(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (mLogEnable) {
            Log.e(tag, getMsgWithLineNumber(msg))
        }
    }

    fun i(tag: String, msg: String) {
        if (mLogEnable) {
            Log.i(tag, getMsgWithLineNumber(msg))
        }
    }

    fun w(tag: String, msg: String) {
        if (mLogEnable) {
            Log.w(tag, getMsgWithLineNumber(msg))
        }
    }

    fun v(tag: String, msg: String) {
        if (mLogEnable) {
            Log.v(tag, getMsgWithLineNumber(msg))
        }
    }


    fun d(msg: String) {
        if (mLogEnable) {
            val content = getMsgAndTagWithLineNumber(msg)
            Log.d(content[0], content[1])
        }
    }

    fun e(msg: String) {
        if (mLogEnable) {
            val content = getMsgAndTagWithLineNumber(msg)
            Log.e(content[0], content[1])
        }
    }

    fun i(msg: String) {
        if (mLogEnable) {
            val content = getMsgAndTagWithLineNumber(msg)
            Log.i(content[0], content[1])
        }
    }

    fun i() {
        if (mLogEnable) {
            val content = getMsgAndTagWithLineNumber("")
            Log.i(content[0], content[1])
        }
    }

    fun w(msg: String) {
        if (mLogEnable) {
            val content = getMsgAndTagWithLineNumber(msg)
            Log.w(content[0], content[1])
        }
    }

    fun v(msg: String) {
        if (mLogEnable) {
            val content = getMsgAndTagWithLineNumber(msg)
            Log.v(content[0], content[1])
        }
    }

    fun d2(tag: String, msg: String) {
        if (mLogEnable) {
            Log.d(tag, msg)
        }
    }

    fun getMsgWithLineNumber(msg: String): String {
        try {
            for (st in Throwable().stackTrace) {
                if (mClassname == st.className || mMethods!!.contains(st.methodName)) {
                    continue
                } else {
                    val b = st.className.lastIndexOf(".") + 1
                    return st.className.substring(b) + "->" + msg
                }

            }
        } catch (e: Exception) {

        }

        return msg
    }

    fun getMsgAndTagWithLineNumber(msg: String): Array<String> {
        try {
            for (st in Throwable().stackTrace) {
                if (mClassname == st.className || mMethods!!.contains(st.methodName)) {
                    continue
                } else {
                    val b = st.className.lastIndexOf(".") + 1
                    val TAG = st.className.substring(b)
                    val message = st.methodName + "():" + st.lineNumber + "->" + msg
                    return arrayOf(TAG, message)
                }

            }
        } catch (e: Exception) {

        }

        return arrayOf("universal tag", msg)
    }
}
