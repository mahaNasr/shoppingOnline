package com.example.shopingonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopingonline.R
import com.example.shopingonline.adapter.CartAdapter
import com.example.shopingonline.model.Cart
import com.example.shopingonline.ui.CartFragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_cart.*

class AddressActivity : AppCompatActivity() {
    var db: FirebaseFirestore? = null
    var TAG = "maha"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        db = FirebaseFirestore.getInstance()

        var data = intent
        var total = data.getDoubleExtra("total", 0.0)
        btn_buynow.setOnClickListener {

            address()
            edfullname.setText(" ")
            ednumberphone.setText(" ")
            edcity.setText(" ")
            edstreet.setText(" ")
            edfulladdress.setText(" ")
            txt_total.setText(" ")
            txt_subtotal.setText(" ")
            txt_shipping.setText(" ")



        }
        btnshow.setOnClickListener {
            linearr.visibility=View.VISIBLE
            txt_subtotal.text= "$total"
            txt_shipping.text="10"
            var totall=total+10
            txt_total.text="$totall"

        }
    }
    fun address(){
        var x=edfullname.text.toString()
        var y=ednumberphone.text.toString()
        var w=edstreet.text.toString()
        var z=edcity.text.toString()
        var t= edfulladdress.text.toString()

        edcity.text.toString()
        if (edfullname.text.toString().isEmpty()) {
            edfullname.error = "Please Enter your name .."
            edfullname.requestFocus()
            return
        }

        if (ednumberphone.text.toString().isEmpty()) {
            ednumberphone.error = "Please Enter your Phone.."
            ednumberphone.requestFocus()
            return
        }
        if (edcity.text.toString().isEmpty()) {
            edcity.error = "Please Enter your City .."
            edcity.requestFocus()
            return
        }

        if (edstreet.text.toString().isEmpty()) {
            edstreet.error = "Please Enter Street .."
            edstreet.requestFocus()
            return
        }


        if (edfulladdress.text.toString().isEmpty()) {
            edfulladdress.error = "Please Enter your Address .."
            edfulladdress.requestFocus()
            return
        }
        if (txt_subtotal.text.toString().equals("0.0")) {
            Toast.makeText(this, "Please Add Products to the Cart .. ", Toast.LENGTH_SHORT).show()

            return
        }
        Toast.makeText(this, "Request Sent", Toast.LENGTH_SHORT).show()

        getAllCart()



    }

    private fun getAllCart(): ArrayList<Cart> {
        val dataa = ArrayList<Cart>()
        db!!.collection("cart")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val cart = document.toObject(Cart::class.java)
                    cart.id = document.id
                    dataa.add(cart)

                     }
                for (i in 0.. dataa.size -1) {
                    db!!.collection("cart").document("${dataa[i].id}")
                        .delete().addOnSuccessListener {
                            dataa.removeAll(dataa)
                            var i= Intent(this, CartFragment::class.java)
                            startActivity(i)
                        }.addOnFailureListener {
                            Log.e("maha", "failed")
                        }
                }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        return dataa
    }

}
