package com.wyuxks.neteasecloud.ui.base

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.wyuxks.neteasecloud.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des :
 */
abstract class BaseFragment : Fragment() {

    lateinit var childView: View
    lateinit var rootView: View
    lateinit var animationDrawable: AnimationDrawable
    // fragment是否显示了
    var mIsVisible: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = View.inflate(activity, R.layout.fragment_base, null)
        childView = View.inflate(activity, setLayout(), null)
        val container = rootView.container
        container.addView(childView)
        return rootView
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationDrawable = img_progress.drawable as AnimationDrawable
        if (!animationDrawable.isRunning) {
            animationDrawable.start()
        }
        ll_error_refresh.setOnClickListener({
            showLoading()
            loadData()
        })
        childView.visibility = View.GONE
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * 显示加载中状态
     */
    protected fun showLoading() {
        if (ll_progress_bar.visibility != View.VISIBLE) {
            ll_progress_bar.setVisibility(View.VISIBLE)
        }
        // 开始动画
        if (!animationDrawable.isRunning()) {
            animationDrawable.start()
        }
        if (childView.visibility != View.GONE) {
            childView.setVisibility(View.GONE)
        }
        if (ll_error_refresh.visibility != View.GONE) {
            ll_error_refresh.setVisibility(View.GONE)
        }
    }

    /**
     * 加载完成的状态
     */
    protected fun showContentView() {
        if (ll_progress_bar.getVisibility() != View.GONE) {
            ll_progress_bar.setVisibility(View.GONE)
        }
        // 停止动画
        if (animationDrawable.isRunning()) {
            animationDrawable.stop()
        }
        if (childView.visibility != View.VISIBLE) {
            childView.setVisibility(View.VISIBLE)
        }
        if (ll_error_refresh.visibility != View.GONE) {
            ll_error_refresh.setVisibility(View.GONE)
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected fun showError() {
        if (ll_progress_bar.getVisibility() != View.GONE) {
            ll_progress_bar.setVisibility(View.GONE)
        }
        // 停止动画
        if (animationDrawable.isRunning()) {
            animationDrawable.stop()
        }

        if (childView.visibility != View.GONE) {
            childView.setVisibility(View.GONE)
        }
        if (ll_error_refresh.visibility != View.VISIBLE) {
            ll_error_refresh.setVisibility(View.VISIBLE)
        }
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            mIsVisible = true
            onVisible()
        } else {
            mIsVisible = false
            onInvisible()
        }
    }

    private fun onInvisible() {

    }

    private fun onVisible() {
//        loadData()
    }

    open fun loadData() {

    }

    abstract fun setLayout(): Int

    abstract fun initView()
}