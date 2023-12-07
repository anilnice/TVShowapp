package com.anil.tvshowapp.remote.data


import com.google.gson.annotations.SerializedName

data class TvShows(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val shows: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)