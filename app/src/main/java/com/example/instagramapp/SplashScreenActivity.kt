package com.example.instagramapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val myAnim = AnimationUtils.loadAnimation(this, R.anim.mytransition)

        imgSplashLogo.startAnimation(myAnim)
        lblSplashTitle.startAnimation(myAnim)
        lblPowerBy.startAnimation(myAnim)
        lblDesmond.startAnimation(myAnim)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 5000)

    }
}