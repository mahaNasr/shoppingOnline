package com.example.shopingonline.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import com.example.shopingonline.R
import com.example.shopingonline.activity.CatViewAllActivity
import com.example.shopingonline.model.Homegrid
import kotlinx.android.synthetic.main.grid_item.view.*

class HomegridAdapter(var activity: Activity, var data: ArrayList<Homegrid>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var root=convertView
        if(root == null)
        {
            root=LayoutInflater.from(activity).inflate(R.layout.grid_item ,null, false)
            root.txtgrid.text=data[position].name
            root.imgg.setImageResource(data[position].image)



        }

          root!!.countiner.setOnClickListener {
              var i= Intent(activity, CatViewAllActivity()::class.java)
              i.putExtra("name","Accessories")

              activity.startActivity(i)
          } // acc

        return root!!



    }

    override fun getItem(position: Int): Any {
        return data[position]   }

    override fun getItemId(position: Int): Long {
        return  data[position].id.toLong()  }

    override fun getCount(): Int {
        return data.size  }
}