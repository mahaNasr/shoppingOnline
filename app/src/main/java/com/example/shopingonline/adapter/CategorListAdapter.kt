import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shopingonline.R
import com.example.shopingonline.activity.CatViewAllActivity
import com.example.shopingonline.model.CategorList
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.categories_item.view.*
import kotlinx.android.synthetic.main.fragment_catergories.view.*

class CategorListAdapter(var activity: Activity, var data: ArrayList<CategorList> , val firestoreDB: FirebaseFirestore) : RecyclerView.Adapter<CategorListAdapter.MyViewHolder>() {
    var db: FirebaseFirestore? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val tvname =itemView.name
        val tvimg = itemView.imgp
        val tvview= itemView.btnview



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.categories_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(data[position].image).into(holder.tvimg)


        holder.itemView.setOnClickListener {
            val i = Intent(activity,CatViewAllActivity::class.java)
            i.putExtra("name", data[position].name)
            Toast.makeText(activity, "${data[position].name}" ,Toast.LENGTH_SHORT).show()
            activity.startActivity(i)


        }


    }


}





