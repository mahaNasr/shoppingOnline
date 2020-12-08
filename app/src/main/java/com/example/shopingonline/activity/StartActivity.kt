package com.example.shopingonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.shopingonline.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Handler().postDelayed({
            val i= Intent(this, SignIn::class.java)
            startActivity(i)
            finish()
        },2000)


        avi.show();
        //avi.smoothToShow();


        YoYo.with(Techniques.ZoomInDown)
            .duration(300)
            .repeat(2)
            .playOn(findViewById(R.id.img_start));

    }
    }

