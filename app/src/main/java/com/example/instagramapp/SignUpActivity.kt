package com.example.instagramapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.instagramapp.database.entities.User
import com.example.instagramapp.viewmodels.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SignUpActivity : AppCompatActivity()
{
    private val viewModel by viewModel<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signin_link_btn.setOnClickListener {
            finish()
        }

        signup_btn.setOnClickListener {
            enableComponents(false)
            createAccount()
        }

        layout_sign_up_relative.isSoundEffectsEnabled = false
        layout_sign_up_relative.setOnClickListener {
            hideSoftKeyboard(this)
            fullname_signup.clearFocus()
            username_signup.clearFocus()
            email_signup.clearFocus()
            password_signup.clearFocus()
        }

        setSignUpButtonEnabled(false)
        enableComponents(true)
        initUI()
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerResult.onEach {
                    if (it) {
                        startActivity(Intent(this@SignUpActivity, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    }
                }.launchIn(this)
            }
        }
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

    private fun createAccount()
    {
        val fullName = fullname_signup.text.toString()
        val userName = username_signup.text.toString()
        val email = email_signup.text.toString()
        val password = password_signup.text.toString()

        when {
            TextUtils.isEmpty(fullName) -> Toast.makeText(
                this,
                "Full Name is required.",
                Toast.LENGTH_LONG
            ).show()
            TextUtils.isEmpty(userName) -> Toast.makeText(
                this,
                "User Name is required.",
                Toast.LENGTH_LONG
            ).show()
            TextUtils.isEmpty(email) -> Toast.makeText(
                this,
                "Email is required.",
                Toast.LENGTH_LONG
            ).show()
            TextUtils.isEmpty(password) -> Toast.makeText(
                this,
                "Password is required.",
                Toast.LENGTH_LONG
            ).show()

            else -> {
                val user = User(
                    username = userName,
                    password = password,
                    fullName = fullName,
                    email = email
                )
                viewModel.register(user)
            }
        }
    }
}
