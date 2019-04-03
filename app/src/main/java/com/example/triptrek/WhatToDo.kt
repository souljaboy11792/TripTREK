package com.example.triptrek

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_what_to_do.*

class WhatToDo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_to_do)

        food.setOnClickListener{
            Toast.makeText(this, "This will display all restaurants in the area selected in a list with cards", Toast.LENGTH_SHORT).show()
        }

        places.setOnClickListener{
            Toast.makeText(this, "This will display all attractions in the area selected in a list with cards", Toast.LENGTH_SHORT).show()
        }

        myday.setOnClickListener{
            Toast.makeText(this, "This will display the weather, traffic conditions etc relevant to the area and the trip", Toast.LENGTH_SHORT).show()
        }
    }
}
