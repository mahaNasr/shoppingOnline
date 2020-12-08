package com.example.shopingonline.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopingonline.activity.AddressActivity
import com.example.shopingonline.R
import com.example.shopingonline.adapter.CartAdapter
import com.example.shopingonline.model.Cart
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*


class CartFragment : Fragment() {
    var db: FirebaseFirestore? = null

    var TAG = "maha"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = FirebaseFirestore.getInstance()
        getAllCart()


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cart, container, false)


        root.btnchack.setOnClickListener {
            var priceT: Double = 0.0

            for (i in 0.. cv_cart.size-1){
                var q = cv_cart[i].ed_quantity.text.toString().toInt()
                var pric= cv_cart[i].txt_price.text.toString().toInt()

                if ((cv_cart[i].ed_quantity.text.toString().toInt() == 0)) {
               Toast.makeText(activity, "Please Enter Product Quantity", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
            }
                priceT = priceT + (pric *q)
   }


            val i = Intent(requireContext(), AddressActivity()::class.java)
            i.putExtra("total",priceT)

            startActivity(i)

        }

        root.btn_goto_product.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,HomeFragment()).commit()
        }

        return root

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

                    cv_cart.layoutManager = LinearLayoutManager(requireActivity())
                    val adapter=CartAdapter(requireActivity(),dataa, db!!)
                    cv_cart.adapter = adapter
                    linear1.visibility=View.GONE
                    linear2.visibility=View.VISIBLE

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        return dataa
    }
}
