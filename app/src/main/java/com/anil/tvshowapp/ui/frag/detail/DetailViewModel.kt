package com.anil.tvshowapp.ui.frag.detail

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anil.tvshowapp.R
import com.anil.tvshowapp.domain.GetTvUseCase
import com.anil.tvshowapp.domain.Show
import com.anil.tvshowapp.util.Constants
import com.bumptech.glide.Glide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getTvUseCase: GetTvUseCase) : ViewModel() {

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

    private val _similar_shows = MutableStateFlow(emptyList<Show>())
    val similar_shows: StateFlow<List<Show>> get() = _similar_shows.asStateFlow()


    private var serial_id: String? = null

    fun setShowID(shodID: String) {
        serial_id = shodID
        getTvShows(serial_id!!)
    }

    private fun getTvShows(serialID: String) {
        viewModelScope.launch {
            try {
                val similarShows = getTvUseCase.getSimilarShows(serialID)
                _similar_shows.value = similarShows
                Log.d("TAG-", "getTvShows similar: ${similarShows.size}")
            } catch (e: Exception) {
                Log.d("TAG-", "getTvShows similar: ${e.message}")
            }
        }
    }


}