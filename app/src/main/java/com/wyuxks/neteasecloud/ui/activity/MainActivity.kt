package com.wyuxks.neteasecloud.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.ImageView
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.base.BaseActivity
import com.wyuxks.neteasecloud.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.wyuxks.neteasecloud.http.rx.RxBus
import com.wyuxks.neteasecloud.http.rx.RxCodeConstants
import com.wyuxks.neteasecloud.ui.adapter.MyFragmentPagerAdapter
import com.wyuxks.neteasecloud.ui.fragment.LeftFragment
import com.wyuxks.neteasecloud.ui.fragment.MiddleFragment
import com.wyuxks.neteasecloud.ui.fragment.RightFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_left.*
import rx.Subscription
import rx.functions.Action1


class MainActivity : BaseActivity(), View.OnClickListener {

    var mFragmentList = ArrayList<Fragment>()
    lateinit var subscription: Subscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavView()
        initViewPager()
        initRxBus()

    }

    private fun initViewPager() {
        mFragmentList.add(LeftFragment())
        mFragmentList.add(MiddleFragment())
        mFragmentList.add(RightFragment())

        val myFragmentPagerAdapter = MyFragmentPagerAdapter(supportFragmentManager, mFragmentList)
        vp_content.adapter = myFragmentPagerAdapter
        vp_content.currentItem = 0
        iv_title_left.isSelected = true

        vp_content.offscreenPageLimit = 2

        vp_content.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                when (position) {
                    0 -> {
                        iv_title_left.setSelected(true)
                        iv_title_middle.setSelected(false)
                        iv_title_right.setSelected(false)
                    }
                    1 -> {
                        iv_title_left.setSelected(false)
                        iv_title_middle.setSelected(true)
                        iv_title_right.setSelected(false)
                    }
                    2 -> {
                        iv_title_left.setSelected(false)
                        iv_title_middle.setSelected(false)
                        iv_title_right.setSelected(true)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
            }
        })
        iv_title_left.setOnClickListener(this)
        iv_title_middle.setOnClickListener(this)
        iv_title_right.setOnClickListener(this)
        left_menu.setOnClickListener(this)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)


    }

    private fun initNavView() {
        val headerView = nav_view.getHeaderView(0)
        val avatar = headerView.findViewById(R.id.iv_avatar) as ImageView
        ImageUtils.displayCircle(avatar, R.drawable.ic_avatar)
        nav_view.setNavigationItemSelectedListener({
            when (it.itemId) {
                R.id.nav_camera -> Toast.makeText(this, "nav_camera", Toast.LENGTH_SHORT).show()
                R.id.nav_send -> Toast.makeText(this, "nav_send", Toast.LENGTH_SHORT).show()
                R.id.nav_share -> Toast.makeText(this, "nav_share", Toast.LENGTH_SHORT).show()
                R.id.nav_manage -> Toast.makeText(this, "nav_manage", Toast.LENGTH_SHORT).show()
                R.id.nav_gallery -> Toast.makeText(this, "nav_gallery", Toast.LENGTH_SHORT).show()
                R.id.nav_slideshow -> Toast.makeText(this, "nav_slideshow", Toast.LENGTH_SHORT).show()
            }
            false
        })


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_title_left -> vp_content.currentItem = 0
            R.id.iv_title_middle -> vp_content.currentItem = 1
            R.id.iv_title_right -> vp_content.currentItem = 2
            R.id.left_menu -> dl_left.openDrawer(Gravity.START)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search -> Toast.makeText(this, "search ", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 不退出程序，进入后台
            moveTaskToBack(true)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * 每日推荐点击"更多"跳转
     */
    private fun initRxBus() {
        subscription = RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE_TO_ONE, String::class.java)
                .subscribe(Action1 {
                    when (it) {
                        "电影" -> vp_content.currentItem = 1
                        else -> {}
                    }

                })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe()
        }
    }
}
