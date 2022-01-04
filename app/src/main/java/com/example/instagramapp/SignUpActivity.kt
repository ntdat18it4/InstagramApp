package com.example.instagramapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.instagramapp.Database.UserDatabase
import com.example.instagramapp.Model.UserModel
import com.example.instagramapp.Model.UserViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity()
{

    private lateinit var userViewModel : UserViewModel
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signin_link_btn.setOnClickListener {
            finish()
        }


        signup_btn.setOnClickListener {
            enableComponents(false)
            startLoadingView(true)
            //createAccount()
        }

        layout_sign_up_relative.setOnTouchListener { _, _ ->

            hideSoftKeyboard(this)
            fullname_signup.clearFocus()
            username_signup.clearFocus()
            email_signup.clearFocus()
            password_signup.clearFocus()

            false
        }
        setSignUpButtonEnabled(false)
        startLoadingView(false)
        enableComponents(true)
        initUI()
    }

    private fun initUI()
    {
        fullname_signup.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?)
            {
                if (fullname_signup.text.toString() != "" &&
                    username_signup.text.toString() != "" &&
                    email_signup.text.toString() != "" &&
                    password_signup.text.toString() != "")
                {
                    setSignUpButtonEnabled(true)
                }
                else
                {
                    setSignUpButtonEnabled(false)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                if (fullname_signup.text.toString() != "" &&
                    username_signup.text.toString() != "" &&
                    email_signup.text.toString() != "" &&
                    password_signup.text.toString() != "")
                {
                    setSignUpButtonEnabled(true)
                }
                else
                {
                    setSignUpButtonEnabled(false)
                }
            }
        })

        username_signup.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?)
            {
                if (fullname_signup.text.toString() != "" &&
                    username_signup.text.toString() != "" &&
                    email_signup.text.toString() != "" &&
                    password_signup.text.toString() != "")
                {
                    setSignUpButtonEnabled(true)
                }
                else
                {
                    setSignUpButtonEnabled(false)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                if (fullname_signup.text.toString() != "" &&
                    username_signup.text.toString() != "" &&
                    email_signup.text.toString() != "" &&
                    password_signup.text.toString() != "")
                {
                    setSignUpButtonEnabled(true)
                }
                else
                {
                    setSignUpButtonEnabled(false)
                }
            }
        })

        email_signup.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?)
            {
                if (isEmailValid(email_signup.text.toString()))
                {
                    lbl_invalid_email.visibility = View.GONE
                }
                else
                {
                    lbl_invalid_email.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                if (fullname_signup.text.toString() != "" &&
                    username_signup.text.toString() != "" &&
                    email_signup.text.toString() != "" &&
                    password_signup.text.toString() != "")
                {
                    setSignUpButtonEnabled(true)
                }
                else
                {
                    setSignUpButtonEnabled(false)
                }
            }
        })

        password_signup.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?)
            {
                if (fullname_signup.text.toString() != "" &&
                    username_signup.text.toString() != "" &&
                    email_signup.text.toString() != "" &&
                    password_signup.text.toString() != "")
                {
                    setSignUpButtonEnabled(true)
                }
                else
                {
                    setSignUpButtonEnabled(false)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                if (fullname_signup.text.toString() != "" &&
                    username_signup.text.toString() != "" &&
                    email_signup.text.toString() != "" &&
                    password_signup.text.toString() != "")
                {
                    setSignUpButtonEnabled(true)
                }
                else
                {
                    setSignUpButtonEnabled(false)
                }
            }
        })
    }

    private fun setSignUpButtonEnabled(isEnabled: Boolean)
    {
        if (isEnabled)
        {
            signup_btn.isEnabled = true
            signup_btn.isClickable = true
            signup_btn.background = resources.getDrawable(R.drawable.rounded_corner_black)
        }
        else
        {
            signup_btn.isEnabled = false
            signup_btn.isClickable = false
            signup_btn.background = resources.getDrawable(R.drawable.rounded_corner_light_gray)
        }
    }

    private fun isEmailValid(email: CharSequence): Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun startLoadingView(start: Boolean)
    {
        if (start)
        {
            layoutLoadingView_SignIn.visibility = View.VISIBLE
            animLoadingView_SignIn.setAnimation("paperplane_lottie_animation.json")
            animLoadingView_SignIn.playAnimation()
            animLoadingView_SignIn.loop(true)
        }
        else
        {
            layoutLoadingView_SignIn.visibility = View.GONE
            animLoadingView_SignIn.cancelAnimation()
        }
    }

    private fun enableComponents(isEnabled: Boolean)
    {
        if (isEnabled)
        {
            signin_link_btn.isEnabled = true
            signup_btn.isEnabled = true
            fullname_signup.isEnabled = true
            username_signup.isEnabled = true
            email_signup.isEnabled = true
            password_signup.isEnabled = true
        }
        else
        {
            signin_link_btn.isEnabled = false
            signup_btn.isEnabled = false
            fullname_signup.isEnabled = false
            username_signup.isEnabled = false
            email_signup.isEnabled = false
            password_signup.isEnabled = false
        }
    }

    private fun hideSoftKeyboard(activity: Activity)
    {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus?.windowToken, 0
        )
    }


}