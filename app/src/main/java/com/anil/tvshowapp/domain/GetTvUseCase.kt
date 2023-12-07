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
}