package com.bitcodetech.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogin : Button
    private lateinit var radioIndia : RadioButton
    private lateinit var radioUs : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {

            val loginFragment = LoginFragment()

            loginFragment.onLoginListener = MyOnLoginListener()

            val input = Bundle()
            input.putString("country", if(radioIndia.isChecked) "India" else "US" )
            loginFragment.arguments = input

            supportFragmentManager. beginTransaction()
                .add(R.id.mainContainer, loginFragment, null)
                .addToBackStack(null)
                .commit()

        }
    }

    private inner class MyOnLoginListener : LoginFragment.OnLoginListener {
        override fun onSuccess(loginFragment: LoginFragment, username: String) {
            mt("Success")
            supportFragmentManager.beginTransaction()
                .remove(loginFragment)
                .commit()
        }

        override fun onFailed(loginFragment: LoginFragment, username: String) {
            mt("Failed")
        }

    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        btnLogin = findViewById(R.id.btnLogin)
        radioIndia = findViewById(R.id.radioIndia)
        radioUs = findViewById(R.id.radioUs)
    }
}