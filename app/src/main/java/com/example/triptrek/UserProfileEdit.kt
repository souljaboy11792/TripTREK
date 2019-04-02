package com.example.triptrek

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_user_profile_edit.*

class UserProfileEdit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile_edit)

        var username: String? = null
        var useremail: String? = null

        val user = FirebaseAuth.getInstance().currentUser
        username = user?.displayName.toString()
        useremail = user?.email.toString()

        editUsername.setText(username)
        editEmail.setText(useremail)
    }
}
