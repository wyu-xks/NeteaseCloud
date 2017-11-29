package com.wyuxks.neteasecloud.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentActivity

import com.wyuxks.neteasecloud.R
import kotlinx.android.synthetic.main.activity_loading.*
import java.util.*

class LoadingActivity : AppCompatActivity() {

    val images = arrayOf(R.drawable.b_1, R.drawable.b_2, R.drawable.b_3, R.drawable.b_4, R.drawable.b_5, R.drawable.b_6)
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        val random = Random()
        val index = random.nextInt(images.size)
        loading_iv.setImageResource(images[index])
        val runnable = Runnable { startMainActivity() }
        jump_tv.setOnClickListener {
            startMainActivity()
            handler.removeCallbacks(runnable)
        }
        handler = Handler()
        handler.postDelayed(runnable, 3000)
    }

    fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out)
        finish()
    }
}
