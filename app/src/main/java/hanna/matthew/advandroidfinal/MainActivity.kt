package hanna.matthew.advandroidfinal

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View


class MainActivity : AppCompatActivity() {

    private var adapter: CollectionAdapter? = null
    private var recyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val service = RetrofitClientInstance.retrofitInstance!!.create(GitHubService::class.java!!)
        val call = service.getAllCollections()
        call.enqueue(object : Callback<List<Collection>> {
            override fun onResponse(call: Call<List<Collection>>, response: Response<List<Collection>>) {
//                progressDoalog.dismiss()
                generateDataList(response.body()!!)
            }

            override fun onFailure(call: Call<List<Collection>>, t: Throwable) {
//                progressDoalog.dismiss()
                Toast.makeText(this@MainActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun generateDataList(photoList: List<Collection>) {
        recyclerView = findViewById<View>(R.id.collectionRecyclerView) as RecyclerView?
        adapter = CollectionAdapter(this, photoList)
        val layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
