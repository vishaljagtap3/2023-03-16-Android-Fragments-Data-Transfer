package com.bitcodetech.mainactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    interface OnLoginListener {
        fun onSuccess(loginFragment: LoginFragment, username : String)
        fun onFailed(loginFragment: LoginFragment, username: String)
    }

    var onLoginListener : OnLoginListener? = null

    private lateinit var country : String

    private lateinit var txtCountry : TextView
    private lateinit var edtUsername : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnValidate : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        country = requireArguments().getString("country").toString()

        val view = inflater.inflate(R.layout.login_fragment, null)
        initViews(view)
        setupListeners()

        return view
    }

    private fun setupListeners() {
        btnValidate.setOnClickListener {
            if(onLoginListener == null) {
                return@setOnClickListener
            }

            if(edtUsername.text.toString().equals("bitcode") && edtPassword.text.toString().equals("bitcode")) {
                onLoginListener!!.onSuccess(this@LoginFragment, edtUsername.text.toString())
            }
            else {
                onLoginListener!!.onFailed(this@LoginFragment, edtUsername.text.toString())
            }
        }
    }

    private fun initViews(view : View) {
        txtCountry = view.findViewById(R.id.txtCountry)
        edtUsername = view.findViewById(R.id.edtUsername)
        edtPassword = view.findViewById(R.id.edtPassword)
        btnValidate = view.findViewById(R.id.btnValidate)

        txtCountry.text = country
    }
}