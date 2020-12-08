package com.example.shopingonline.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopingonline.R
import com.example.shopingonline.adapter.CartAdapter
import com.example.shopingonline.adapter.CatDetailsAdapter
import com.example.shopingonline.model.AppConstants
import com.example.shopingonline.model.Cart
import com.example.shopingonline.model.CategorView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_categor_details.*
import kotlinx.android.synthetic.main.cart_item.*
import kotlinx.android.synthetic.main.fragment_cart.*


class CategorDetailsActivity : AppCompatActivity() {
    var db: FirebaseFirestore? = null
    var TAG = "maha"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categor_details)

        db = FirebaseFirestore.getInstance()


        var data = intent
        var namee =data.getStringExtra("name")
        var namee_cat =data.getStringExtra("name_cat")
      //  var price=data.getIntExtra("price",-1)


        viewCategorie(namee_cat.toString())

   // Toast.makeText(this, "$namee" , Toast.LENGTH_SHORT).show()

    // Toast.makeText(this, "$namee_cat" , Toast.LENGTH_SHORT).show()


        when (namee) {
            "Clothing" ->
                ArrayAdapter.createFromResource(
                    this,
                    R.array.Clothing,
                    android.R.layout.select_dialog_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.select_dialog_item)
                    spinner.adapter = adapter

                }
            "Shoes" ->
                ArrayAdapter.createFromResource(
                    this,
                    R.array.Shoes,
                    android.R.layout.select_dialog_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.select_dialog_item)
                    spinner.adapter = adapter

                }
             else ->
                    ArrayAdapter.createFromResource(
                        this,
                        R.array.other,
                        android.R.layout.select_dialog_item
                    ).also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.select_dialog_item)
                        spinner.adapter = adapter

                    }
    }

}


    private fun viewCategorie(namee_cat: String): ArrayList<CategorView> {
        var data2 = ArrayList<CategorView>()

        db!!.collection("categoriesviewall")

            .whereEqualTo("name_cat", namee_cat)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val categoreview = document.toObject(CategorView::class.java)
                    categoreview.id = document.id
                    data2.add(categoreview)
                    cv_cat_details.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
                    val adapter= CatDetailsAdapter(this, data2, db!!)
                    cv_cat_details.adapter=adapter

                    }

               btncart.setOnClickListener {
                    var size = spinner.selectedItem.toString()
                    var name= data2[0].name_cat
                    var price= data2[0].price
                    var image=data2[0].img
                    if (size == "Size:") {
                        Toast.makeText(this, " Please Enter A product Size", Toast.LENGTH_SHORT).show()
                    } else if (size == "Size:OS") {
                        addCart(name.toString(), price, image.toString(), size)

                    } else {
                        addCart(name.toString(), price, image.toString(), size)

                    }




                }
                    // Toast.makeText(this,  , Toast.LENGTH_SHORT).show()



            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }


        return data2
    }


    private fun addCart(name: String, price:Int , img:String ,  size:String) {

        var user = Cart(name ,price ,img , size )


        db!!.collection(AppConstants.COLLECTION_CART)
            .add(user)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()

                Log.d(
                    "maha","DocumentSnapshot added with ID:  ${documentReference.id}")


            }.addOnFailureListener { e ->
                Toast.makeText(this,e.message.toString(), Toast.LENGTH_SHORT).show()

                Log.w("maha",
                    "Error adding document",
                    e
                )
            }

    }




}

