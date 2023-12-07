package com.anil.tvshowapp.ui

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import coil.compose.rememberAsyncImagePainter
import com.anil.tvshowapp.TvShowsApplication
import com.anil.tvshowapp.domain.Show
import com.anil.tvshowapp.util.Constants
import com.anil.tvshowapp.util.PreferencesManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowItem(item: Show, view: View, id: Int) {
    val image = rememberAsyncImagePainter(model = Constants.IMAGE_URL + item.posterPath)

    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        onClick = {
            PreferencesManager(TvShowsApplication.appContext).saveTvShowData(item)
            val bundle = bundleOf("show" to item)
            view.findNavController().navigate(id, bundle)

        }
    ) {
        Box(
            modifier = Modifier.padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alpha = 1f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Text(
                    text = item.originalName,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVShowCard(show: Show, view: View, id: Int) {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        onClick = {
            val bundle = bundleOf("show" to show)
            view.findNavController().navigate(id, bundle)
        }) {
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(200.dp)
                .padding(4.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val actorImg =
                    rememberAsyncImagePainter(model = Constants.IMAGE_URL + show.posterPath)

                Image(
                    painter = actorImg,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Text(
                    text = show.originalName,
                    modifier = Modifier.width(120.dp),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = show.firstAirDate,
                    modifier = Modifier.width(120.dp),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}