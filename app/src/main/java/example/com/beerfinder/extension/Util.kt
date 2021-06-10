package example.com.beerfinder.extension

import android.view.View

fun View.onClick(onClickListener: (View) -> Unit) {
    this.setOnClickListener(onClickListener)
}