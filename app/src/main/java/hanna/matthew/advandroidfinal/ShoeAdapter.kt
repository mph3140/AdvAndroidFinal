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


class ShoeAdapter(private val context: Context, private val shoeList: List<Shoe>) :
    RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    inner class ShoeViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        var txtTitle: TextView
        val shoeImage: ImageView

        init {

            txtTitle = mView.findViewById<TextView>(R.id.title)
            shoeImage = mView.findViewById<ImageView>(R.id.shoeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.shoe_row, parent, false)
        return ShoeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.txtTitle.text = shoeList[position].name

        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context))
        builder.build().load(shoeList[position].shoeImageURL)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.shoeImage)

    }

    override fun getItemCount(): Int {
        return shoeList.size
    }
}