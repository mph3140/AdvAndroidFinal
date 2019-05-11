package hanna.matthew.advandroidfinal.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_add_new_shoe.*
import android.content.Intent
import android.widget.Button
import android.app.Activity
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import hanna.matthew.advandroidfinal.R
import hanna.matthew.advandroidfinal.data.Shoe
import java.io.IOException
import java.util.*


class AddNewShoe : AppCompatActivity() {

    private val pickImageRequest = 1
    private var imageView: ImageView? = null
    val PREFS_NAME = "prefs"
    val PREF_DARK_THEME = "dark_theme"
    var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false)
        if (useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_shoe)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            var shoeName = findViewById<EditText>(R.id.shoeName)
            var shoeId = findViewById<EditText>(R.id.shoeId)
            var shoeBrand = findViewById<EditText>(R.id.shoeBrand)
            var shoeImage = findViewById<ImageView>(R.id.shoeImageView)

            //Test below, realized I wouldn't be able to do this easily the I have set up so I just left it.
            val newShoe = Shoe(10, shoeName.text.toString(), 1, filePath.toString())
            //Set values in object, send values to client
            //return result to activity
            val intent = Intent()
            intent.putExtra("NewShoe", newShoe)
            setResult(RESULT_OK, intent)
            finish()
        }
        val buttonChoose = findViewById<Button>(R.id.buttonChoose)
        imageView = findViewById(R.id.shoeImageView)
        buttonChoose.setOnClickListener(View.OnClickListener {
            showFileChooser()
        })
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), pickImageRequest)
//        val intent = Intent(
//            Intent.ACTION_PICK,
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        )
//        intent.type = "image/*"
//        startActivityForResult(intent, pickImageRequest)
    }

    //handling the image chooser activity result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pickImageRequest && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageView!!.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}
