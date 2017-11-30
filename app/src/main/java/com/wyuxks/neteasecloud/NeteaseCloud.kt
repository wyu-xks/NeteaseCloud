package com.wyuxks.neteasecloud

import android.app.Application

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class NeteaseCloud : Application() {
    companion object {
        fun get(): NeteaseCloud = Instance.instance
    }

    private object Instance {
        val instance = NeteaseCloud()
    }
}