package com.example.triptrek

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log.d
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        var username: String? = null
        var useremail: String? = null

        val user = FirebaseAuth.getInstance().currentUser
        username = user?.displayName.toString()
        useremail = user?.email.toString()

        Username.setText(username)
        Email.setText(useremail)


        Toast.makeText(this, "username = $username, useremail = $useremail", Toast.LENGTH_SHORT).show()

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
