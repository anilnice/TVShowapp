package com.anil.tvshowapp.domain

import com.anil.tvshowapp.repo.TvShowRepository
import javax.inject.Inject

class GetTvUseCase @Inject constructor(private val tvShowRepository: TvShowRepository) {

    suspend fun getTvshows(tvShowName: String): List<Show> {
        return tvShowRepository.getTVShows(tvShowName)
    }
}