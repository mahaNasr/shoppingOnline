package com.example.shopingonline.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopingonline.R
import com.example.shopingonline.activity.CatViewAllActivity
import com.example.shopingonline.adapter.HomeAdapter
import com.example.shopingonline.adapter.HomegridAdapter
import com.example.shopingonline.model.Home
import com.example.shopingonline.model.Homegrid
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)



        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data= ArrayList<Home>()
        data.add(Home( 1, R.drawable.r11))
        data.add(Home( 2, R.drawable.r12))
        data.add(Home(3,  R.drawable.r13))
        data.add(Home( 4, R.drawable.r14))
        data.add(Home( 5, R.drawable.r15))

        cv_home.layoutManager= LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL,false)
        val adapter=HomeAdapter(requireActivity(), data)
        cv_home.adapter=adapter




        val data2= ArrayList<Homegrid>()
        data2.add(Homegrid( 1,"Wild Color Elastic Bracelet", R.drawable.i1))
        data2.add(Homegrid( 2,"Iconic Lumine Link Watch, 32mm", R.drawable.i2))
        data2.add(Homegrid(3, "Chain And Pearl Drop Earrings ", R.drawable.i3))
        data2.add(Homegrid( 4,"Round Sunglasses", R.drawable.i4))


        val countryAdapter=HomegridAdapter(requireActivity(), data2)
        gv_imag.adapter=countryAdapter


       makup.setOnClickListener {
           var i=Intent(activity, CatViewAllActivity()::class.java)
           i.putExtra("name","Beauty")

           startActivity(i)
       }
        sport.setOnClickListener {
            var i=Intent(activity, CatViewAllActivity()::class.java)
            i.putExtra("name","Sport")

            startActivity(i)
        }

    }

}

