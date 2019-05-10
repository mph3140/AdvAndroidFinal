package hanna.matthew.advandroidfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_shoe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoeActivity : AppCompatActivity() {
    private var adapter: ShoeAdapter? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoe)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent
        val collection = intent.getIntExtra("ShoeType", 0)
        val service = RetrofitClientInstance.retrofitInstance!!.create(GitHubService::class.java!!)
        val call = service.getAllShoes()
        call.enqueue(object : Callback<List<Shoe>> {
            override fun onResponse(call: Call<List<Shoe>>, response: Response<List<Shoe>>) {
//                progressDoalog.dismiss()
                val shoes = response.body()!!
                val shoesToAdd: ArrayList<Shoe>         //Had issues with modifying, after writing iterator realized it was probabaly because val, just going to leave it
                shoesToAdd = shoes as ArrayList<Shoe>
                val iterator = shoesToAdd.iterator()
                while (iterator.hasNext()) {
                    val value = iterator.next()
                    if (value.collectionId != collection) {
                        iterator.remove()
                    }
                }
                generateShoeList(shoesToAdd)
            }

            override fun onFailure(call: Call<List<Shoe>>, t: Throwable) {
//                progressDoalog.dismiss()
                Toast.makeText(this@ShoeActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /*Method to generate List of data using RecyclerView with collection adapter*/
    private fun generateShoeList(shoeList: List<Shoe>) {
        recyclerView = findViewById(R.id.shoeRecyclerView)
        adapter = ShoeAdapter(this, shoeList)
        val layoutManager = LinearLayoutManager(this@ShoeActivity)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapter
    }
}