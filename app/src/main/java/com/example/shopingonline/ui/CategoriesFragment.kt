package com.example.shopingonline.ui

import CategorListAdapter
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopingonline.activity.CatViewAllActivity
import com.example.shopingonline.R
import com.example.shopingonline.model.AppConstants
import com.example.shopingonline.model.AppConstants.Companion.COLLECTION_CATEGORIES
import com.example.shopingonline.model.CategorList
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_catergories.*
import java.util.ArrayList

class CategoriesFragment : Fragment() {
    var db: FirebaseFirestore? = null
    var TAG = "maha"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       db = FirebaseFirestore.getInstance()




    }



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

       return inflater.inflate(R.layout.fragment_catergories, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val list = ArrayList<String>()
        //val d = mutableListOf<String>()
         list.add("Clothing"); list.add("Shoes"); list.add("Accessories");list.add("Beauty");  list.add("Bags"); list.add("Homeware")
         ;list.add("Sport"); list.add("Scarf")

        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, list)
        lv_cat.adapter = arrayAdapter

        viewCategorie("Clothing")

        lv_cat.setOnItemClickListener { adapterView, view, i, l ->
          //  view.setBackgroundColor(Color.CYAN)

           val namee= lv_cat.getItemAtPosition(i)
          //  Toast.makeText(requireContext()," $namee",Toast.LENGTH_SHORT).show()

            viewCategorie(namee.toString())

            btnview.setOnClickListener {

                val i = Intent(activity, CatViewAllActivity::class.java)
                 i.putExtra("name", "$namee")
                  Toast.makeText(activity, "$namee" ,Toast.LENGTH_SHORT).show()
                   startActivity(i)

            }

        }



    }

    private fun viewCategorie(namee: String): ArrayList<CategorList> {
        var data2 = ArrayList<CategorList>()
        db!!.collection(AppConstants.COLLECTION_CATEGORIES)

            .whereEqualTo("name", namee)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val categore = document.toObject(CategorList::class.java)
                    categore.id = document.id
                    data2.add(categore)

                    // Toast.makeText(this,  , Toast.LENGTH_SHORT).show()
                    cv_cat.layoutManager = LinearLayoutManager(requireContext())
                    val adapter = CategorListAdapter(requireActivity(), data2, db!!)
                    cv_cat.adapter = adapter


                }


            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
        return data2
    }
}
