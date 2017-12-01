package com.huayuni.kotlinlearn.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 *  Author : xks
 *  Data : 2017/10/16 0016
 *  Des : log 工具类
 */

val LOG_TAG = "LOG"
var DEBUG_OPEN = true

fun Any?.d(message: Any?) {
    if (DEBUG_OPEN)
        Log.d(LOG_TAG, message.toString())
}

fun Any?.i(message: Any?) {
    if (DEBUG_OPEN)
        Log.i(LOG_TAG, message.toString())
}

fun e(message: Any?) {
    if (DEBUG_OPEN)
        Log.e(LOG_TAG, message.toString())
}

fun Any?.toast(context: Context, message: Any?) {
    Toast.makeText(context.applicationContext, message.toString(), Toast.LENGTH_SHORT).show()
}


