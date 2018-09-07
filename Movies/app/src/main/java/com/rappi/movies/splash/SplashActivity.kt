package com.rappi.movies.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Handler
import android.os.Bundle
import android.view.WindowManager
import com.rappi.movies.R
import com.rappi.movies.introPager.IntroPagerActivity

class SplashActivity : AppCompatActivity() {

    private val TIME_OUT = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent = Intent(this, IntroPagerActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, TIME_OUT)
    }
}
