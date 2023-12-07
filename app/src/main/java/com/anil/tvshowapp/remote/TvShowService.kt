package com.anil.tvshowapp.remote

import com.anil.tvshowapp.remote.data.Result
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
}