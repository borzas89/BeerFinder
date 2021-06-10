package example.com.beerfinder.ui.list

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.xwray.groupie.databinding.BindableItem
import example.com.beerfinder.R
import example.com.beerfinder.databinding.ItemHeaderBinding
import example.com.beerfinder.extension.onClick
import example.com.beerfinder.model.MarkerPresentationModel

class HeaderItem(private val marker: MarkerPresentationModel,
                 var onHeaderItemClicked: MarkerItemClickedlambda) : BindableItem<ItemHeaderBinding>(){


    override fun getLayout(): Int {
        return R.layout.item_header
    }

    override fun bind(viewBinding: ItemHeaderBinding, position: Int) {

        viewBinding.title.text = marker.city

        viewBinding.headerLayout.onClick {
            onHeaderItemClicked.invoke(marker, true)
        }


    }


}