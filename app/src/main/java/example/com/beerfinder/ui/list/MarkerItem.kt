package example.com.beerfinder.ui.list

import android.text.Html
import android.view.View
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem
import example.com.beerfinder.R
import example.com.beerfinder.databinding.MarkerListItemBinding
import example.com.beerfinder.extension.load
import example.com.beerfinder.model.MarkerPresentationModel

typealias MarkerItemClickedlambda = (marker: MarkerPresentationModel, headerCLicked: Boolean) -> Unit
class MarkerItem(private val marker: MarkerPresentationModel,
                 var onItemClicked: MarkerItemClickedlambda) : BindableItem<MarkerListItemBinding>(){


    override fun getLayout(): Int {
        return R.layout.marker_list_item
    }


    override fun bind(viewBinding: MarkerListItemBinding, position: Int) {
        viewBinding.markerImage.load(marker.urlToImage)
        viewBinding.titleText.text = marker.name
        viewBinding.addressText.text = marker.address
        viewBinding.descriptionText.text = Html.fromHtml(marker.description)

        viewBinding.markerItemLayout.setOnClickListener {
            onItemClicked.invoke(marker, false)
        }

    }

    override fun getSpanSize(spanCount: Int, position: Int)
            = spanCount/2;



}