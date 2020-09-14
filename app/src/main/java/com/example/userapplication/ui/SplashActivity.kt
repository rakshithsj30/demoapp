package com.example.userapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.userapplication.R

private const val SPLASH_TIME_OUT:Long = 5000 // 5 sec

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this,ImageListActivity::class.java))
            // close this activity
            finish()
        }, SPLASH_TIME_OUT) ggg
    }
}
