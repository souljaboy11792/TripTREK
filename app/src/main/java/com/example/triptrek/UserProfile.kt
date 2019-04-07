package com.example.triptrek

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log.d
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.activity_user_profile_edit.*

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

        var photourl: String? = null

        val uid = FirebaseAuth.getInstance().uid
        if ( uid == null )
        {
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val user = p0.getValue(User::class.java)
                photourl = user?.getphotourl().toString()
                d("TT", "within Listener, photourl = $photourl")
                ProfilePhoto.setText("")
                ProfilePhoto.alpha = 0f
                Picasso.get().load(photourl).into(photoFiller)
            }

            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        Toast.makeText(this, "username = $username, useremail = $useremail", Toast.LENGTH_SHORT).show()

        newTrip.setOnClickListener {
            val intent = Intent(this, AreaSelect::class.java)
            startActivity(intent)
        }

        editProfile.setOnClickListener {
            val intent = Intent(this, UserProfileEdit::class.java)
            startActivity(intent)
        }

        signOut.setOnClickListener {
            val user = FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
