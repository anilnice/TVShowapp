package com.anil.tvshowapp.domain

import android.os.Parcelable
import com.anil.tvshowapp.remote.data.showdetails.Season
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvSeason(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_count")
    val episodeCount: Int?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int?,
    @SerializedName("vote_average")
    val voteAverage: Double
) : Parcelable


fun Season.toTvSeason() =
    TvSeason(airDate, episodeCount, id, name, overview, posterPath, seasonNumber, voteAverage)