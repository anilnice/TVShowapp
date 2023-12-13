package com.anil.tvshowapp.domain

import com.anil.tvshowapp.repo.TvShowRepository
import javax.inject.Inject

class GetTvUseCase @Inject constructor(private val tvShowRepository: TvShowRepository) {

    suspend fun getTvshows(tvShowName: String): List<Show> {
        return tvShowRepository.getTVShows(tvShowName)
    }

    suspend fun getSimilarShows(series_id: String): List<Show> {
        return tvShowRepository.getSimilarShows(series_id)
    }

    suspend fun getWeekTrendShows(): List<Show> {
        return tvShowRepository.getWeekTrendShows()
    }

    suspend fun getShowSeasons(series_id: String): List<TvSeason> {
        return tvShowRepository.getShowDetails(series_id)
    }
}