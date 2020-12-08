package com.example.shopingonline.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopingonline.R
import com.example.shopingonline.model.CategorView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cat_details_item.view.*

class CatDetailsAdapter (var activity: Activity, var data: ArrayList<CategorView>, val firestoreDB: FirebaseFirestore) : RecyclerView.Adapter<CatDetailsAdapter.MyViewHolder>() {
    var db: FirebaseFirestore? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvname = itemView.txtnamecatd
        val tvprice = itemView.txtpricecd
        val tvimg = itemView.imgc
        val tvimg2 = itemView.imgc2



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(activity).inflate(R.layout.cat_details_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvname.text = data[position].name_cat
        holder.tvprice.text = data[position].price.toString()
        Picasso.get().load(data[position].img).into(holder.tvimg)
        Picasso.get().load(data[position].img2).into(holder.tvimg2)


        }



}





