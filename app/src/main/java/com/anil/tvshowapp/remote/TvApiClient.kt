package com.anil.tvshowapp.remote

import com.anil.tvshowapp.remote.data.TvShows
import com.anil.tvshowapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvApiClient {
    @GET(Constants.TV_SHOW_SEARCH)
    suspend fun getTvShows(@Query("query") query: String): Response<TvShows>
}