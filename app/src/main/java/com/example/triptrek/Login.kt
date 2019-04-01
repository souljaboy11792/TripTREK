package com.example.triptrek

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        SignUpButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser()
    {
        val user = username.text.toString()
        val email = email.text.toString()
        val password = password.text.toString()

        if ( email.isEmpty() || password.isEmpty() )
        {
            Toast.makeText(this, "Please enter at least email address and password", Toast.LENGTH_SHORT).show()
            return
        }
        else
        {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if ( it.isSuccessful )
                        {
                            Toast.makeText(this, "User sign up successful", Toast.LENGTH_SHORT).show()
                            d("TT", "User created with email: $email, password: $password and user id: ${it.result.user.uid}")
                            return@addOnCompleteListener
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "User sign up failed. ${it.message}", Toast.LENGTH_SHORT).show()
                        d("TT", "User sign up failed. ${it.message}")
                        return@addOnFailureListener
                    }
        }
    }

}
