package com.example.triptrek

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        SignInButton.setOnClickListener {
            val email = email.text
            val username = username.text
            val password = password.text

            d("TT", "Username: $username, email: $email password: $password")
        }
    }

}
