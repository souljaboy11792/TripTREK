package com.example.triptrek

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        newTrip.setOnClickListener {
            val intent = Intent(this, AreaSelect::class.java)
            startActivity(intent)
        }

        editProfile.setOnClickListener {
            val intent = Intent(this, UserProfileEdit::class.java)
            startActivity(intent)
        }
    }
}
