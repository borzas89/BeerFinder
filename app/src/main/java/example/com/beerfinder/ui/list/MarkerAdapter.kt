package example.com.beerfinder.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import example.com.beerfinder.R
import example.com.beerfinder.extension.load
import example.com.beerfinder.extension.onClick
import example.com.beerfinder.model.MarkerPresentationModel


typealias ItemClickedlambda = (v: View, position: Int) -> Unit

class MarkerAdapter(var onItemClicked: ItemClickedlambda): RecyclerView.Adapter<MarkerAdapter.MarkerViewHolder>() {

    var markers: List<MarkerPresentationModel> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder  {
            val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.marker_list_item, parent, false)
        return MarkerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        val marker = markers[position]
        holder.bind(marker)
    }

    override fun getItemCount(): Int = markers.size

    fun updateData(data: List<MarkerPresentationModel>) {
        this.markers = data
        notifyDataSetChanged()
    }


    inner class MarkerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val titleText: TextView
        private val addressText: TextView
        private val descriptionText: TextView
        private val markerImage: ImageView


        init {
            itemView.onClick {
                val adapterPosition = adapterPosition.takeIf { it >= 0 } ?: return@onClick
                onItemClicked.invoke(it,adapterPosition)
            }
            titleText = itemView.findViewById(R.id.titleText)
            addressText = itemView.findViewById(R.id.addressText)
            descriptionText = itemView.findViewById(R.id.descriptionText)
            markerImage = itemView.findViewById(R.id.markerImage)
        }

        fun bind(marker: MarkerPresentationModel) {
            titleText.text = marker.name
            markerImage.load(marker.urlToImage)

        }


    }


}