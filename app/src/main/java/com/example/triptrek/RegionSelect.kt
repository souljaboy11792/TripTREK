package com.example.triptrek

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_region_select.*

class RegionSelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region_select)

        val adapter = GroupAdapter<ViewHolder>()

        adapter.add(region())
        adapter.add(region())
        adapter.add(region())
        adapter.add(region())
        adapter.add(region())
        adapter.add(region())

        region_recycleview.adapter = adapter
    }
}

class region: Item<ViewHolder>()
{
    override fun bind(viewHolder: ViewHolder, position: Int) {
    }

    override fun getLayout(): Int {
        return R.layout.region_recycler
    }
}
