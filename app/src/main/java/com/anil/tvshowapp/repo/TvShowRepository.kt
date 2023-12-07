package com.anil.tvshowapp.repo

import com.anil.tvshowapp.domain.Show
import com.anil.tvshowapp.domain.toShow
import com.anil.tvshowapp.remote.TvShowService
import javax.inject.Inject

class TvShowRepository @Inject constructor(private val tvShowService: TvShowService) {

    suspend fun getTVShows(tvShowName: String): List<Show> {
        return tvShowService.getTVShows(tvShowName).map {
            it.toShow()
        }
    }
}