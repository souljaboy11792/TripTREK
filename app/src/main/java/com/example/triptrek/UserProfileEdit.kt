package com.example.triptrek

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log.d
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_area_select.*
import kotlinx.android.synthetic.main.activity_user_profile_edit.*
import java.util.*

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

        selectProfilePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        editOk.setOnClickListener {
            updateProfilePhoto()
        }
    }

    var uri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( requestCode == 0 && resultCode == Activity.RESULT_OK && data != null)
        {
            uri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            selectProfilePhoto.setBackgroundDrawable(bitmapDrawable)
        }
    }

    private fun updateProfilePhoto()
    {
        if ( uri == null )
            return

        var url: String? = ""
        val file = UUID.randomUUID().toString()
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseStorage.getInstance().getReference("/userphotos/$uid/$file")

        ref.putFile(uri!!)
                .addOnSuccessListener {
                    Toast.makeText(this, "Added image to ${it.metadata?.path}", Toast.LENGTH_SHORT).show()
                    ref.downloadUrl
                            .addOnSuccessListener {
                                url = it.toString()
                                d("TT", "Url: $url")
                                Toast.makeText(this, "Photo url: $it", Toast.LENGTH_SHORT).show()

                                val dbref = FirebaseDatabase.getInstance().getReference("users")
                                dbref.child("$uid").child("photo").setValue(url)
                                        .addOnSuccessListener {
                                            d("TT", "Successfully modified database and added $url to users/$uid/photo field")
                                        }
                                        .addOnFailureListener {
                                            d("TT", "${it.message}")
                                        }
                            }
                }


    }
}
