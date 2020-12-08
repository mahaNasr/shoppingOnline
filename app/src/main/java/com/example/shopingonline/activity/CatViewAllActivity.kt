package com.example.shopingonline.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopingonline.R
import com.example.shopingonline.adapter.CategorViewAllAdapter
import com.example.shopingonline.model.CategorView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_cat_view_all.*

class CatViewAllActivity : AppCompatActivity() {
    var db: FirebaseFirestore? = null
    var TAG = "maha"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_view_all)

        db = FirebaseFirestore.getInstance()


        var data = intent
        var namee = data.getStringExtra("name")
        viewCategorie(namee.toString())

    }
    private fun viewCategorie(namee: String): ArrayList<CategorView> {
        var data2 = ArrayList<CategorView>()
        db!!.collection("categoriesviewall")

            .whereEqualTo("name", namee)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val categoreview = document.toObject(CategorView::class.java)
                    categoreview.id = document.id
                    data2.add(categoreview)
                    val numberOfColumns = 2
                    // Toast.makeText(this,  , Toast.LENGTH_SHORT).show()

                    cv_cat_view_all.setLayoutManager(GridLayoutManager(this, numberOfColumns))

                    val adapter = CategorViewAllAdapter(this, data2 , db!!)
                    cv_cat_view_all.adapter =adapter


                }


            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
        return data2
    }
}
