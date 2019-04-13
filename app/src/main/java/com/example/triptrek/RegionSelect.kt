package com.example.triptrek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log.d
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_region_select.*
import kotlinx.android.synthetic.main.region_recycler.view.*

class RegionSelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region_select)

        val region = "Dhanmondi"

        fetchspots(region)

    }

    var spotarray: Array<spot> = arrayOf()
    var i: Int = 0

    private fun fetchspots(region: String)
    {
        val adapter = GroupAdapter<ViewHolder>()

        val ref = FirebaseDatabase.getInstance().getReference("/places/$region")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    val spot_tmp = it.getValue(spot::class.java)
                    if ( spot_tmp != null ) {
                        adapter.add(spotsHolder(spot_tmp, spotarray))
                    }
                }
                region_recycleview.adapter = adapter
            }
        })
    }

}


class spotsHolder(val spots: spot, val spotarraytmp: Array<spot>): Item<ViewHolder>()
{
    override fun bind(viewHolder: ViewHolder, position: Int){
        val last: Int = spotarraytmp.lastIndex
        viewHolder.itemView.name.text = spots?.name.toString()
        //viewHolder.itemView.about.movementMethod = ScrollingMovementMethod()
        viewHolder.itemView.about.text = spots?.about.toString()
        viewHolder.itemView.latitude.text = spots?.latitude.toString()
        viewHolder.itemView.longitude.text = spots?.longitude.toString()
        d("TT", "Photo url = ${spots?.picture.toString()}")
        Picasso.get().load(spots?.picture).into(viewHolder.itemView.image)
        viewHolder.itemView.add_to_list.setOnClickListener {
            spotarraytmp[last+1] = spots
        }
//        viewHolder.itemView.list.setOnClickListener {
//            val intent = Intent(this, List::class.java)
//            intent.putExtra()
//        }
    }
    override fun getLayout(): Int {
        return R.layout.region_recycler
    }
}

class spot(val name: String, val about: String, val latitude: Double, val longitude: Double, val picture: String)
{
    constructor() : this("", "", 0.0, 0.0, "")
}