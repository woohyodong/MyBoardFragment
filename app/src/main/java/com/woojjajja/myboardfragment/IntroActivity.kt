package com.woojjajja.myboardfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.woojjajja.myboardfragment.ui.MainActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Handler().postDelayed(
            Runnable {
                startActivity(Intent(this@IntroActivity,MainActivity::class.java))
                finish()
            },1000
        )
    }
}