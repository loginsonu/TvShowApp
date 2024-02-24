package com.example.tvshowapp.presentation.tv_show_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowapp.common.Resource
import com.example.tvshowapp.domain.use_case.get_tv_shows.GetTvShowsUseCase
import com.example.tvshowapp.domain.use_case.search_tv_show_use_case.GetTvShowsSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val getTvShowsSearchUseCase: GetTvShowsSearchUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TvShowListState())
    val state: State<TvShowListState> = _state

    init {
        getTvShows()
    }

    private fun getTvShows() {

        getTvShowsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = TvShowListState(tvShows = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = TvShowListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = TvShowListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onQueryChange(query: String) {
        _state.value = state.value.copy(
           stateSearchText = query
        )

    }

    fun isSearchActive(value:Boolean){
        _state.value = state.value.copy(
            isSearch=value
        )
    }

    fun onSearch(query:String){
        _state.value = state.value.copy(
            isSearch=false
        )
        if(query.isEmpty()){
            getTvShows()
        }else{
            searchTvShows(query)
        }

    }

    private fun searchTvShows(query:String) {
        getTvShowsSearchUseCase(query).onEach { result->
            when (result) {
                is Resource.Success -> {
                    _state.value = TvShowListState(tvShows = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = TvShowListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = TvShowListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun clearSearch() {
        _state.value = state.value.copy(
            stateSearchText=""
        )
    }

    fun closeSearch() {
        _state.value = state.value.copy(
            stateSearchText="",
            isSearch = false
        )
    }
}