package dev.givaldo.feature.main.extention

import android.widget.ImageView
import com.squareup.picasso.Picasso
import dev.givaldo.feature.R

fun ImageView.setPicassoImage(path: String) {
    Picasso.get()
        .load(path)
        .placeholder(R.drawable.image_default_placeholder)
        .fit()
        .into(this)
}