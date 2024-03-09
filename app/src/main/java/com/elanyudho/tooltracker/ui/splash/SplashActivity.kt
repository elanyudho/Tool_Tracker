package com.elanyudho.tooltracker.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.tooltracker.databinding.ActivitySplashBinding
import com.elanyudho.tooltracker.ui.main.MainActivity

class SplashActivity : BaseActivityBinding<ActivitySplashBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding
        get() = { ActivitySplashBinding.inflate(layoutInflater) }

    override fun setupView() {
        Handler(Looper.getMainLooper()).postDelayed({
            moveNext()
        }, 2000L)
    }

    private fun moveNext() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }


}