package com.example.yourwellbeing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_Screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)

        supportActionBar?.hide()

        Handler().postDelayed(
            {
                startActivity(Intent(this,detail_taker_activity::class.java))
            },1600
        )

    }
}
