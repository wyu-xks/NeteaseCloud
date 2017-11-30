package com.wyuxks.neteasecloud.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wyuxks.neteasecloud.R

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des :
 */
open abstract class BaseFragment : Fragment() {

    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = View.inflate(activity, setLayout(), null)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun setLayout(): Int

    abstract fun initView()
}