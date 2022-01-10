package com.example.instagramapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
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
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.instagramapp.viewmodels.SignInViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignInActivity : AppCompatActivity()
{
    private val viewModel by viewModel<SignInViewModel>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        buttonOnClickListener()
        textFieldValidationChecking()

        setLoginButtonEnabled(false)
        enableComponents(true)

        lifecycleScope.launch {
            viewModel.loginStatus
                .onEach {
                    if (it) {
                        startActivity(Intent(this@SignInActivity, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    }
                }
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .launchIn(this)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun buttonOnClickListener()
    {
        signup_link_btn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            email_login.text = null
            password_login.text = null
            lblInvalidEmail.visibility = View.GONE
        }

        login_btn.setOnClickListener {
            enableComponents(false)
            setLoginButtonEnabled(false)
            loginUser()
        }

        layout_sign_in_relative.setOnTouchListener { _, _ ->
            if (email_login.text.toString() != "" && !isEmailValid(email_login.text.toString()))
            {
                lblInvalidEmail.visibility = View.VISIBLE
            }
            else
            {
                lblInvalidEmail.visibility = View.GONE
            }

            hideSoftKeyboard(this)
            email_login.clearFocus()
            password_login.clearFocus()
            false
        }
    }

    private fun textFieldValidationChecking()
    {
        email_login.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?)
            {
                if (!isEmailValid(email_login.text.toString()))
                {
                    lblInvalidEmail.visibility = View.VISIBLE
                }
                else
                {
                    lblInvalidEmail.visibility = View.GONE
                    if (email_login.text.toString() != "" && password_login.text.toString() != "")
                    {
                        setLoginButtonEnabled(true)
                    }
                    else
                    {
                        setLoginButtonEnabled(false)
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                if (email_login.text.toString() != "" && password_login.text.toString() != "")
                {
                    setLoginButtonEnabled(true)
                }
                else
                {
                    setLoginButtonEnabled(false)
                }
            }
        })

        password_login.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?)
            {
                if (email_login.text.toString() != "" && password_login.text.toString() != "")
                {
                    setLoginButtonEnabled(true)
                }
                else
                {
                    setLoginButtonEnabled(false)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
                if (email_login.text.toString() != "" && password_login.text.toString() != "")
                {
                    setLoginButtonEnabled(true)
                }
                else
                {
                    setLoginButtonEnabled(false)
                }
            }
        })
    }

    fun isEmailValid(email: CharSequence): Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun setLoginButtonEnabled(isEnabled: Boolean)
    {
        if (isEnabled)
        {
            login_btn.isEnabled = true
            login_btn.isClickable = true
            login_btn.background = ResourcesCompat.getDrawable(resources, R.drawable.rounded_corner_black, null)
        }
        else
        {
            login_btn.isEnabled = false
            login_btn.isClickable = false
            login_btn.background = ResourcesCompat.getDrawable(resources, R.drawable.rounded_corner_light_gray, null)
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


    private fun enableComponents(isEnabled: Boolean)
    {
        if (isEnabled)
        {
            signup_link_btn.isEnabled = true
            email_login.isEnabled = true
            password_login.isEnabled = true

        }
        else
        {
            signup_link_btn.isEnabled = false
            email_login.isEnabled = false
            password_login.isEnabled = false
        }
    }

    private fun loginUser()
    {
        val email = email_login.text.toString()
        val password = password_login.text.toString()

        when
        {
            TextUtils.isEmpty(email) -> Toast.makeText(this, "Email is required.", Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(password) -> Toast.makeText(this, "Password is required.", Toast.LENGTH_LONG).show()

            else ->
            {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Login")
                progressDialog.setMessage("Please wait, this may take a while...")
                progressDialog.setCanceledOnTouchOutside(false)
                //progressDialog.show()

                viewModel.signIn(email, password)
            }
        }
    }
}
