package hanna.matthew.advandroidfinal

import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


private class CollectionAdapter(private val context: Context, private val collectionList: List<Collection>) :
    RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    internal inner class CollectionViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        var txtTitle: TextView
        val collectionImage: ImageView

        init {

            txtTitle = mView.findViewById<TextView>(R.id.title)
            collectionImage = mView.findViewById<ImageView>(R.id.collectionImage)
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
        builder.build().load(R.drawable.default_image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.collectionImage)

    }

    override fun getItemCount(): Int {
        return collectionList.size
    }
}