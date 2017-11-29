package com.wyuxks.neteasecloud.ui.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.wyuxks.neteasecloud.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun checkPermission(){

    }
}
