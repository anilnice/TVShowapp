package com.anil.tvshowapp.ui.frag.detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.anil.tvshowapp.R
import com.anil.tvshowapp.util.Constants
import com.bumptech.glide.Glide

class DetailViewModel : ViewModel() {

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun setImage(image: ImageView, url: String?) {

            if (!url.isNullOrEmpty()) {
                Glide.with(image.context).load(Constants.IMAGE_URL + url).centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(image)
            }

        }
    }
}