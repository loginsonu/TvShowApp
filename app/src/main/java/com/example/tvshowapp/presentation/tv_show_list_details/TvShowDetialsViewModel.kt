package com.example.tvshowapp.presentation.tv_show_list_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowapp.common.Constants
import com.example.tvshowapp.common.Resource
import com.example.tvshowapp.domain.use_case.get_similar_tv_show_use_case.GetSimilarTvShowsUseCase
import com.example.tvshowapp.domain.use_case.get_tv_show_details.GetTvShowsDetailsUseCase
import com.example.tvshowapp.domain.use_case.get_tv_shows.GetTvShowsUseCase
import com.example.tvshowapp.domain.use_case.search_tv_show_use_case.GetTvShowsSearchUseCase
import com.example.tvshowapp.presentation.tv_show_list.TvShowListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val getTvShowsDetailsUseCase: GetTvShowsDetailsUseCase,
    private val getSimilarTvShowsUseCase: GetSimilarTvShowsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(TvShowDetailsState())

    val state: State<TvShowDetailsState> = _state

    private val _stateSimilar = mutableStateOf(SimilarTvShowState())

    val stateSimilar: State<SimilarTvShowState> = _stateSimilar

    init {
        savedStateHandle.get<String>(Constants.PARAM_TV_SHOW_ID)?.let { id ->
            getTvShowDetails(id.toInt())
            getSimilarTvShowDetails(id.toInt())
        }
    }
    private fun getTvShowDetails(id: Int) {
        getTvShowsDetailsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = TvShowDetailsState(tvShowsDetails = result.data)
                }
                is Resource.Error -> {
                    _state.value = TvShowDetailsState(error =result.message ?: "An unexpected error occured" )
                }
                is Resource.Loading -> {
                    _state.value = TvShowDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSimilarTvShowDetails(id: Int) {
        getSimilarTvShowsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                      _stateSimilar.value = SimilarTvShowState(similarTvShows = result.data?: emptyList())

                }
                is Resource.Error -> {

                    _stateSimilar.value = SimilarTvShowState(error = result.message ?: "An unexpected error occured" )
                }
                is Resource.Loading -> {
                    _stateSimilar.value = SimilarTvShowState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}