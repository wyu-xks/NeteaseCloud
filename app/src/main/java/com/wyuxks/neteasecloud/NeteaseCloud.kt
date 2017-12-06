package com.wyuxks.neteasecloud

import android.app.Application

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class NeteaseCloud : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {
        lateinit var instance: NeteaseCloud
            private set
    }

//    companion object {
//        fun get(): NeteaseCloud = Instance.INSTANCE
//    }
//
//    private object Instance {
//        val INSTANCE = NeteaseCloud()
//    }
}