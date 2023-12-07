package com.anil.tvshowapp.util

class Constants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val TV_SHOW_SEARCH = "search/tv?"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
        const val TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YjllYjBjOTVmMzAwMmU2MzQ4ZmM1YmNmZGZkZjIxOCIsInN1YiI6IjVmMTE0NTU3YzYxNmFjMDAzMzVjYTE0NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ssIzSbr3IW-3DAaxNWyeRY5iiywnyclLz0w4V-3giOU"

        const val SHARED_TV_SHOW = "tvshows"
        const val MOVIE_CREDITS = "{movie_id}/credits"
        const val MOVIE_REVIEWS = "{movie_id}/reviews"
        const val MOVIE_ID = "movie_id"
    }
}