package com.example.tvshowapp.presentation.tv_show_list_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.tvshowapp.R
import com.example.tvshowapp.common.Constants
import com.example.tvshowapp.data.localdb.TvShowFav
import com.example.tvshowapp.domain.model.TvShow
import com.example.tvshowapp.presentation.Screen
import com.example.tvshowapp.presentation.tv_show_list.TvShowListViewModel
import com.example.tvshowapp.presentation.tv_show_list_details.components.SimilarItem
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowDetailScreen(
    navController: NavController,
    viewModel: TvShowDetailsViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val similarState = viewModel.stateSimilar.value

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    state.tvShowsDetails?.let {
                        Text(
                            text = it.name,
                            color = MaterialTheme.colorScheme.onPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                },
                actions = {
                    if(!state.hideFav){
                        Image(
                            modifier = Modifier
                                .padding(5.dp)
                                .clip(CircleShape)
                                .clickable {
                                    state.tvShowsDetails?.let {
                                        val tvShow = TvShowFav(
                                            id = state.tvShowsDetails.id,
                                            name = state.tvShowsDetails.name,
                                            posterPath = state.tvShowsDetails.posterPath)
                                        if(state.showFavSelected)
                                            viewModel.removeFavTvShow(tvShow)
                                        else
                                            viewModel.saveFavTvShow(tvShow)


                                    }
                                }
                            ,
                            painter = painterResource(id = R.drawable.baseline_stars_24),
                            contentDescription = "Fav",
                            colorFilter =
                            if(state.showFavSelected)
                                ColorFilter.tint(Color.Green)
                            else
                                ColorFilter.tint(Color.Black)
                        )

                    }

                }
            )

        }
    ) { padding->
        Box(modifier = Modifier.fillMaxSize().padding(padding)){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    state.tvShowsDetails?.let {tvShow->
                        item {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
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

                        if (tvShow.overview.isNotEmpty()) {
                            item {
                                Text(
                                    text = stringResource(id = R.string.descriptions),
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                                    textAlign = TextAlign.Start,
                                )
                                Text(
                                    text = tvShow.overview,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(
                                            start = 10.dp,
                                            end = 10.dp,
                                            top = 15.dp,
                                            bottom = 15.dp
                                        ),
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }

                        item {
                            Text(
                                text = stringResource(id = R.string.seasons),
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 8.dp),
                                textAlign = TextAlign.Start,
                            )
                        }

                        items(tvShow.seasons){
                            Text(
                                text = it.name + "  -  Episodes (${it.episodeCount})",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 20.dp,
                                        end = 20.dp,
                                        top = 6.dp,
                                        bottom = 6.dp
                                    ),
                                textAlign = TextAlign.Justify,
                            )
                            Divider(
                                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                        }
                    }

                    if(similarState.similarTvShows.isNotEmpty()){
                        item {
                            Text(
                                text = stringResource(id = R.string.similar_shows),
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp, top = 26.dp),
                                textAlign = TextAlign.Start,
                            )
                            LazyRow(
                                modifier = Modifier
                                    .padding(5.dp).fillMaxWidth()
                            ) {
                                items(similarState.similarTvShows) { similarTvShows ->
                                    SimilarItem(similarTvShows) {tvShow->
                                        navController.navigate(Screen.TvShowDetailScreen.route + "/${tvShow.id}/other")
                                    }
                                }
                            }
                        }
                    }

                }


            if(state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

        }



    }
}