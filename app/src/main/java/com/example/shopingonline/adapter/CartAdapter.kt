package com.example.shopingonline.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shopingonline.R
import com.example.shopingonline.activity.SignIn
import com.example.shopingonline.model.AppConstants
import com.example.shopingonline.model.Cart
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_categor_details.view.*
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.view.*

class CartAdapter (var activity: Activity, var data: ArrayList<Cart>, val firestoreDB: FirebaseFirestore) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    var db: FirebaseFirestore? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvname = itemView.txt_name
        val tvprice = itemView.txt_price
        val tvimg = itemView.img
        val quantity = itemView.ed_quantity
        val tvsize = itemView.txtname
    //    val tvbtncheckout= itemView.btnchack
        val tvimgdelet =itemView.imgdelet
        val add = itemView.btnblus
        val minus = itemView.btnmin


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.cart_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvname.text = data[position].name_cat
        holder.tvprice.text = data[position].price.toString()
        holder.tvsize.text=data[position].size
       // holder.quantity.text= data[position].quantity
        Picasso.get().load(data[position].img).into(holder.tvimg)
        //*************
        holder.tvimgdelet.setOnClickListener {

            val alertDialog = AlertDialog.Builder(activity)
            alertDialog.setTitle("Delete the product")
            alertDialog.setMessage("Are you sure ?")
            alertDialog.setCancelable(false)
            alertDialog.setPositiveButton("Yes") { _, _ ->
                deleteProductById(data[position].id!! , position)
                //Toast.makeText(activity, "Yes", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setNegativeButton("No") { _, _ ->
               // Toast.makeText(activity, "No", Toast.LENGTH_SHORT).show()
            }


            alertDialog.create().show()
            true
        }
//********

        var q = holder.quantity.text.toString().toInt()
        q = 0

        holder.add.setOnClickListener {
            if (q >= 0) {
                q = q + 1
                holder.quantity.text = q.toString()

            }
        }

        holder.minus.setOnClickListener {
            if (q > 0) {
                q = q - 1
                holder.quantity.text = q.toString()
            }
        }



    }

    private fun deleteProductById(id: String , position: Int) {

        db = FirebaseFirestore.getInstance()

        db!!.collection("cart").document(id)
            .delete().addOnSuccessListener {
                data.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeRemoved(position, data.size)
                Toast.makeText(activity,"delete sucessfully",Toast.LENGTH_LONG).show()

            }.addOnFailureListener {
                Log.e("maha", "failed")
            }
    }



}

