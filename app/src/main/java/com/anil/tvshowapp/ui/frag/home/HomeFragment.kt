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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anil.tvshowapp.R
import com.anil.tvshowapp.databinding.FragmentHomeBinding
import com.anil.tvshowapp.domain.Show
import com.anil.tvshowapp.ui.TvShowItem
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
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.composeView.setContent {
            TvShowList()
        }
        binding.searchShow.setOnEditorActionListener(object : OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    binding.searchShow.hideKeyboard()
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
    fun TvShowList() {
        val tv_shows by viewModel.tv_shows.collectAsState()
        Log.d("TAG-", "tvShowList: ${tv_shows.size}")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(tv_shows) { item: Show ->
                TvShowItem(item, requireView(), R.id.action_homeFragment_to_detailFragment)
            }
        }
    }


}