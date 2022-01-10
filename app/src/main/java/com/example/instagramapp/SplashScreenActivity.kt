package com.example.instagramapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val myAnim = AnimationUtils.loadAnimation(this, R.anim.mytransition)

        imgSplashLogo.startAnimation(myAnim)
        lblSplashTitle.startAnimation(myAnim)
        lblPowerBy.startAnimation(myAnim)
        lblDesmond.startAnimation(myAnim)

        val signInIntent = Intent(this, SignInActivity::class.java)

        val timer = object : Thread()
        {
            override fun run()
            {
                try
                {
                    sleep(3000)
                } catch (e: InterruptedException)
                {
                    e.printStackTrace()
                } finally
                {
                    startActivity(signInIntent)
                    finish()
                }
            }
        }
        timer.start()
    }
}