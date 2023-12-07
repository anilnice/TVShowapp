package com.anil.tvshowapp.ui.frag.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import coil.compose.rememberAsyncImagePainter
import com.anil.tvshowapp.R
import com.anil.tvshowapp.TvShowsApplication
import com.anil.tvshowapp.databinding.FragmentHomeBinding
import com.anil.tvshowapp.domain.Show
import com.anil.tvshowapp.util.Constants.Companion.IMAGE_URL
import com.anil.tvshowapp.util.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.composeView.setContent {
            tvShowList()
        }
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.searchShow.setOnEditorActionListener(object : OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    binding.searchShow.hideKeyboard()
                    Toast.makeText(context, p0?.text, Toast.LENGTH_LONG).show()
                    viewModel.setTvshowName(p0?.text.toString())
                    return true
                }
                return false
            }

        })
        return binding.root
    }

    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    @Composable
    fun tvShowList() {
        val tv_shows by viewModel.tv_shows.collectAsState()
        Log.d("TAG-", "tvShowList: ${tv_shows.size}")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(tv_shows) { item: Show ->
                TvShowItem(item)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TvShowItem(item: Show) {
        val image = rememberAsyncImagePainter(model = IMAGE_URL + item.posterPath)

        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            onClick = {
                PreferencesManager(TvShowsApplication.appContext).saveTvShowData(item)
                view?.findNavController()?.navigate(R.id.action_homeFragment_to_detailFragment)

            }
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alpha = 1f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
        }
    }


}