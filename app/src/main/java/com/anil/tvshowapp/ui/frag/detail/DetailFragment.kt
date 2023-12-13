package com.anil.tvshowapp.ui.frag.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anil.tvshowapp.R
import com.anil.tvshowapp.databinding.FragmentDetailBinding
import com.anil.tvshowapp.domain.Show
import com.anil.tvshowapp.domain.TvSeason
import com.anil.tvshowapp.ui.SeasonEpisodeCard
import com.anil.tvshowapp.ui.TVShowCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding = FragmentDetailBinding.inflate(inflater)
        val show = arguments?.getParcelable("show", Show::class.java)
        binding.tvshow = show
        viewModel.setShowID(show?.id.toString())
        binding.similarShows.setContent {
            Similarshows()
        }
        return binding.root
    }

    @Composable
    private fun Similarshows() {
        val similarshow by viewModel.similar_shows.collectAsState()
        val showSeasons by viewModel.show_seasons.collectAsState()
        Column {
            LazyRow(
                Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(similarshow) { item: Show ->
                    TVShowCard(show = item, requireView(), R.id.action_detailFragment_self)
                }
            }
            Text(
                text = stringResource(R.string.episodes),
                Modifier.padding(4.dp),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
            LazyRow(
                Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(showSeasons) { item: TvSeason ->
                    SeasonEpisodeCard(item, requireView(), R.id.action_detailFragment_self)
                }
            }
        }
    }


}