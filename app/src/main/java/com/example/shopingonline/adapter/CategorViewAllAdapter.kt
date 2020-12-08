package com.example.shopingonline.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shopingonline.activity.CategorDetailsActivity
import com.example.shopingonline.R
import com.example.shopingonline.model.CategorView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_categorieviewall.view.*

class CategorViewAllAdapter (var activity: Activity, var data: ArrayList<CategorView>, val firestoreDB: FirebaseFirestore) : RecyclerView.Adapter<CategorViewAllAdapter.MyViewHolder>() {
    var db: FirebaseFirestore? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvname = itemView.txtnamec
        val tvprice = itemView.txtpricec
        val tvimg = itemView.imgc


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(activity).inflate(R.layout.item_categorieviewall, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: CategorViewAllAdapter.MyViewHolder, position: Int) {

        holder.tvname.text = data[position].name_cat
        holder.tvprice.text = data[position].price.toString()
        Picasso.get().load(data[position].img).into(holder.tvimg)


        holder.tvimg.setOnClickListener {
            val i = Intent(activity, CategorDetailsActivity::class.java)
            i.putExtra("name", data[position].name)
            i.putExtra("price",data[position].price)
            i.putExtra("name_cat", data[position].name_cat)

       //    Toast.makeText(activity, "${data[position].name_cat}" ,Toast.LENGTH_SHORT).show()
            activity.startActivity(i)


        }
    }
}





