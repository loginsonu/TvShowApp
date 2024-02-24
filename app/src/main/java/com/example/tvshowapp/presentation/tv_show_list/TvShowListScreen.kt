package com.example.tvshowapp.presentation.tv_show_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tvshowapp.R
import com.example.tvshowapp.presentation.Screen
import com.example.tvshowapp.presentation.tv_show_list.components.TvShowListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowListScreen(
    navController: NavController,
    viewModel: TvShowListViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val searchHistory = remember { mutableStateListOf<String>() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
           TopAppBar(
               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = MaterialTheme.colorScheme.primary,
                   actionIconContentColor = MaterialTheme.colorScheme.onPrimary
               ),
               title = {
                   Text(
                       text = stringResource(id = R.string.app_name),
                       color = MaterialTheme.colorScheme.onPrimary
                   )
               },
               actions = {
                   IconButton(
                       onClick = {viewModel.isSearchActive(true) }
                   ) {
                       Icon(
                           imageVector = Icons.Default.Search,
                           contentDescription = stringResource(id = R.string.search_hint)
                       )
                   }
               }
           )
        }
    ) { padding->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ){
                items(state.tvShows){tvShow->
                    TvShowListItem(
                        tvShow = tvShow
                    ){

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

            if (state.isSearch) {
                SearchBar(
                    query = state.stateSearchText,
                    onQueryChange = {
                        viewModel.onQueryChange(it)
                    },
                    onSearch = {
                        if(it.isNotEmpty()) searchHistory.add(it)
                        viewModel.onSearch(it)
                    },
                    active = true,
                    onActiveChange = {
                        viewModel.isSearchActive(it)
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.search_hint))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.search_hint)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.clickable {
                                if (state.stateSearchText.isNotEmpty()) {
                                    viewModel.clearSearch()
                                } else {
                                    viewModel.closeSearch()
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(id = R.string.search_close)
                        )
                    }
                ) {
                    // previously searched history
                    searchHistory.forEach {
                        Row(modifier = Modifier.padding(all = 14.dp)) {
                            Image(
                                modifier = Modifier.size(24.dp).padding(end = 10.dp),
                                painter = painterResource(id = R.drawable.history),
                                contentDescription = stringResource(id = R.string.history) )
                            Text(text = it)
                        }
                    }
                }
            }
        }

    }
}