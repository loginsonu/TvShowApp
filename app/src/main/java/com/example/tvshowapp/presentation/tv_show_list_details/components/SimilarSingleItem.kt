package com.example.tvshowapp.presentation.tv_show_list_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tvshowapp.R
import com.example.tvshowapp.common.Constants
import com.example.tvshowapp.domain.model.TvShow

@Composable
fun SimilarItem(tvShow: TvShow, onItemClick: () -> Unit){
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(5.dp)
            .size(120.dp)
            .clickable {
                onItemClick()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
            ,
            painter = if(tvShow.posterPath.isNotEmpty())
                rememberAsyncImagePainter(Constants.BASE_IMAGE_URL+tvShow.posterPath)
            else
                painterResource(id = R.drawable.no_image_found)
            ,
            contentDescription ="image",
            contentScale = ContentScale.FillBounds
        )
    }
}