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

    suspend fun getSimilarShows(series_id: String): List<Show> {
        return tvShowService.getSimilarShows(series_id).map {
            it.toShow()
        }
    }

    suspend fun getWeekTrendShows(): List<Show>{
        return tvShowService.getWeekTrendShows().map {
            it.toShow()
        }
    }
}