package com.anil.tvshowapp.ui.frag.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anil.tvshowapp.domain.GetTvUseCase
import com.anil.tvshowapp.domain.Show
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getTvUseCase: GetTvUseCase) : ViewModel() {

    private val _tv_shows = MutableStateFlow(emptyList<Show>())
    val tv_shows: StateFlow<List<Show>> get() = _tv_shows.asStateFlow()


    private var tvShowName: String? = null

    fun setTvshowName(showName: String) {
        tvShowName = showName
        getTvShows(tvShowName!!)
    }

    private fun getTvShows(tvShowName: String) {
        viewModelScope.launch {
            try {
                val tvShows = getTvUseCase.getTvshows(tvShowName)
                _tv_shows.value = tvShows
                Log.d("TAG-", "getTvShows: ${tvShows.size}")
            } catch (e: Exception) {
                Log.d("TAG-", "getTvShows: ${e.message}")
            }
        }
    }


}