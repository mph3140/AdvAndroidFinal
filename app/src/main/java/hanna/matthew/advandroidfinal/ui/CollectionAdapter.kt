package hanna.matthew.advandroidfinal.ui

import android.content.Context
import android.content.Intent
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import hanna.matthew.advandroidfinal.data.Collection
import hanna.matthew.advandroidfinal.R


class CollectionAdapter(private var context: Context, private val collectionList: List<Collection>) :
    RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    inner class CollectionViewHolder(val mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener {

        var txtTitle: TextView
        val collectionImage: ImageView

        init {
            txtTitle = mView.findViewById<TextView>(R.id.title)
            collectionImage = mView.findViewById<ImageView>(R.id.collectionImage)
            mView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            // Get the position of the item that was clicked.
            val mPosition = layoutPosition
            // Use that to access the affected item in mWordList.
            val element = collectionList[mPosition]
            // Change the word in the mWordList.
//            mWordList.set(mPosition, "Clicked! $element")
            val intent = Intent(v.context, ShoeActivity::class.java)
            intent.putExtra("ShoeType", element.collectionId)
            startActivity(v.context, intent, null)
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
//            mAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.collection_row, parent, false)
        return CollectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.txtTitle.text = collectionList[position].name

        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context))
        builder.build().load(collectionList[position].brandLogoURL)
            .placeholder(R.drawable.ic_launcher_background)
            .resize(200, 200)
            .error(R.drawable.ic_launcher_background)
            .into(holder.collectionImage)

    }

    override fun getItemCount(): Int {
        return collectionList.size
    }
}