package hanna.matthew.advandroidfinal

import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_add_new_shoe.*
import android.content.Intent
import android.widget.Button
import android.R.attr.bitmap
import android.app.Activity
import android.provider.MediaStore
import android.widget.ImageView
import java.io.IOException


class AddNewShoe : AppCompatActivity() {

    private val pickImageRequest = 1
    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_shoe)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            var shoeName = findViewById<EditText>(R.id.shoeName)
            var shoeId = findViewById<EditText>(R.id.shoeId)
            var showBrand = findViewById<EditText>(R.id.shoeBrand)
            //Set values in object, send values to client
            //return result to activity
            setResult(RESULT_OK)
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
            val filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageView!!.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}
