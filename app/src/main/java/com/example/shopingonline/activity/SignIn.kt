package com.example.shopingonline.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns

import android.view.View
import android.widget.Toast
import com.example.shopingonline.R
import com.example.shopingonline.model.AppConstants
import com.example.shopingonline.model.UserRE
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {
    var db: FirebaseFirestore? = null
    var TAG = "maha"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        db = FirebaseFirestore.getInstance()

        btnsignin.setOnClickListener {
            linearsignin.visibility = View.VISIBLE
            linearsignup.visibility = View.GONE
            btnsignin.setBackgroundResource(R.color.color1)
            btnsignup.setBackgroundResource(R.color.color2)

        }
        btnsignup.setOnClickListener {
            linearsignin.visibility = View.GONE
            linearsignup.visibility = View.VISIBLE
            btnsignin.setBackgroundResource(R.color.color2)
            btnsignup.setBackgroundResource(R.color.color1)

        }
        btn_signup.setOnClickListener {
            SignupUser()
        }

        btn_signin.setOnClickListener {
         //   SignInUser()
            val i = Intent(this, MainActivity()::class.java)
            startActivity(i)

            finish()
        }


    }

    private fun SignupUser() {
        var passwordd = edPass.text.toString()
        var email = edemailsignup.text.toString()

        if (edemailsignup.text.toString().isEmpty()) {
            edemailsignup.error = "Please Enter Email.."
            edemailsignup.requestFocus()
            return
        }

        if (edPass.text.toString().isEmpty() && edaginpass.text.toString().isEmpty()) {
            edPass.error = "Please Enter Password.."
            edPass.requestFocus()
            return
        }

        if (edPass.text.toString() != edaginpass.text.toString()) {
            edPass.error = "Please Enter valid Password.."
            edPass.requestFocus()
            return
        }
        if (TextUtils.isDigitsOnly(passwordd)) {
            edPass.error = "Password must contain letters and numbers"
            edPass.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(edemailsignup.text.toString()).matches()) {
            edemailsignup.error = "Please Enter valid Email"
            return
        }

        addUser(email, passwordd)
    }

    //*************
    private fun addUser(email: String, password: String) {

        var user = UserRE(email, password)


        db!!.collection(AppConstants.COLLECTION)
            .add(user)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

                linearsignin.visibility = View.VISIBLE
                linearsignup.visibility = View.GONE
                btnsignin.setBackgroundResource(R.color.color1)
                btnsignup.setBackgroundResource(R.color.color2)


                Log.d(
                    "maha", "DocumentSnapshot added with ID:  ${documentReference.id}"
                )


            }.addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()

                Log.w(
                    "maha",
                    "Error adding document",
                    e
                )
            }
    }


    private fun SignInUser() {
        var passwordd = edpassword.text.toString()
        var email = edemail.text.toString()


        if (edemail.text.toString().isEmpty()) {
            edemail.error = "Please Enter Email.."
            edemail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(edemail.text.toString()).matches()) {
            edemail.error = "Please Enter valid Email"
            return
        }

        if (edpassword.text.toString().isEmpty()) {
            edpassword.error = "Please Enter Password.."
            edpassword.requestFocus()
            return
        }

     getAllUser()



    }


    private fun getAllUser(): ArrayList<UserRE> {
        val dataa = ArrayList<UserRE>()
        db!!.collection("userre")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val user = document.toObject(UserRE::class.java)
                    user.id = document.id
                    dataa.add(user)

                }
                var b = 0
                for (i in 0..dataa.size - 1) {
                    if (dataa[i].email == edemail.text.toString() && dataa[i].password == edpassword.text.toString()) {

                val i = Intent(this, MainActivity()::class.java)
                startActivity(i)
                        b++
                finish()

                    }
                }
                if (b == 0) {
                    Toast.makeText(
                        baseContext, " Sign in failed ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        return dataa
    }
}
