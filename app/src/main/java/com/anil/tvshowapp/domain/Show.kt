package com.anil.tvshowapp.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.anil.tvshowapp.remote.data.Result
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("favorites")
data class Show(
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("first_air_date")
    var firstAirDate: String,
    /*@SerializedName("genre_ids")
    @Ignore
    var genreIds: List<Int>,*/
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("name")
    var name: String,
    /*@SerializedName("origin_country")
    @Ignore
    var originCountry: List<String>,*/
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_name")
    var originalName: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("popularity")
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Int
) : Parcelable{

}

fun Result.toShow() = Show(
    adult,
    backdropPath,
    firstAirDate,
    //genreIds,
    id,
    name,
    //originCountry,
    originalLanguage,
    originalName,
    overview,
    popularity,
    posterPath,
    voteAverage,
    voteCount
)