package com.anil.tvshowapp.remote

import com.anil.tvshowapp.remote.data.Result
import com.anil.tvshowapp.remote.data.showdetails.Season
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowService @Inject constructor(private val tvApiClient: TvApiClient) {

    suspend fun getTVShows(tvShowName: String): List<Result> {
        return withContext(Dispatchers.IO) {
            val tvShows = tvApiClient.getTvShows(tvShowName)
            tvShows.body()!!.shows
        }
    }

    suspend fun getSimilarShows(series_id: String): List<Result> {
        return withContext(Dispatchers.IO) {
            val similarShows = tvApiClient.getSimilarShows(series_id)
            similarShows.body()!!.shows
        }
    }

    suspend fun getWeekTrendShows(): List<Result> {
        return withContext(Dispatchers.IO) {
            val weekTrendShows = tvApiClient.getWeekTrendShow()
            weekTrendShows.body()!!.shows
        }
    }

    suspend fun getShowDetails(series_id: String): List<Season> {
        return withContext(Dispatchers.IO) {
            val showDetails = tvApiClient.getShowDetails(series_id)
            showDetails.body()!!.seasons
        }
    }
}