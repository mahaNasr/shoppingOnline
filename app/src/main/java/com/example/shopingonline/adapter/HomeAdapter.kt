package com.example.shopingonline.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopingonline.R
import com.example.shopingonline.activity.CatViewAllActivity
import com.example.shopingonline.model.Home
import kotlinx.android.synthetic.main.view_item.view.*

class HomeAdapter(var activity: Activity, var data: ArrayList<Home>) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvimg = itemView.imgc
        val mainContainer = itemView.mainContainer  /// لو عملت onClick  عليها
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.view_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvimg.setImageResource(data[position].imag)


        holder.itemView.mainContainer.setOnClickListener {


                var i=Intent(activity, CatViewAllActivity()::class.java)
                    i.putExtra("name","Clothing")

                activity.startActivity(i)


        }


    }
}


