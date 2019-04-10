package com.example.triptrek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_region_select.*
import kotlinx.android.synthetic.main.region_recycler.view.*

class RegionSelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region_select)

        val region = "Dhanmondi"

        fetchspots(region)


//        val adapter = GroupAdapter<ViewHolder>()
//
//        adapter.add(region())
//        adapter.add(region())
//        adapter.add(region())
//        adapter.add(region())
//        adapter.add(region())
//        adapter.add(region())
//
//        region_recycleview.adapter = adapter
    }

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
                        adapter.add(spotsHolder(spot_tmp))
                    }
                }
                region_recycleview.adapter = adapter
            }
        })
    }
}

class spotsHolder(val spots: spot): Item<ViewHolder>()
{
    //constructor() : this("", "", null, null, "")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.name.text = spots?.name.toString()
        viewHolder.itemView.about.text = spots?.about.toString()
        viewHolder.itemView.latitude.text = spots?.latitude.toString()
        viewHolder.itemView.longitude.text = spots?.longitude.toString()

    }
    override fun getLayout(): Int {
        return R.layout.region_recycler
    }
}

class spot(val name: String, val about: String, val latitude: Double, val longitude: Double, val photourl: String)
{
    constructor() : this("", "", 0.0, 0.0, "")
}