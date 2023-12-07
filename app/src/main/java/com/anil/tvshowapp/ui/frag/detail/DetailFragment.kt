package com.anil.tvshowapp.ui.frag.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anil.tvshowapp.TvShowsApplication
import com.anil.tvshowapp.databinding.FragmentDetailBinding
import com.anil.tvshowapp.domain.Show
import com.anil.tvshowapp.ui.TVShowCard
import com.anil.tvshowapp.util.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding = FragmentDetailBinding.inflate(inflater)
        val movie = PreferencesManager(TvShowsApplication.appContext).getTvShowData()
        binding.tvshow = movie
        viewModel.setShowID(movie.id.toString())
        binding.similarShows.setContent {
            Similarshows()
        }
        return binding.root
    }

    @Composable
    private fun Similarshows() {
        val similarshow by viewModel.similar_shows.collectAsState()
        LazyRow(
            Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(similarshow) { item: Show ->
                TVShowCard(show = item)
            }
        }
    }


}