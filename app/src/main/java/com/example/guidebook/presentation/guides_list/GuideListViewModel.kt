package com.example.guidebook.presentation.guides_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guidebook.common.Resource
import com.example.guidebook.data.local.model.GuideEntity
import com.example.guidebook.domain.repository.GuideBookRepository
import com.example.guidebook.domain.use_case.get_guides.GetGuidesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideListViewModel @Inject constructor(
    private val getGuidesUseCase: GetGuidesUseCase,
    private val repository: GuideBookRepository
) : ViewModel() {
    var stateDb by mutableStateOf(emptyList<GuideEntity>())

    private val _state = mutableStateOf(GuideListState())
    val stateApi: State<GuideListState> = _state

    init {
        getGuidesApi()
    }

    fun getGuidesApi(isRefreshing: Boolean = false) {
        getGuidesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = GuideListState(guides = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        GuideListState(error = result.message ?: "Unexpected error occurred")
                }
                is Resource.Loading -> {
                    if (isRefreshing) {
                        _state.value = GuideListState(isRefreshing = true)
                    } else {
                        _state.value = GuideListState(isLoading = true)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun getGuidesDb(){
        viewModelScope.launch {
            try {
                repository.getGuidesDb().collect { value ->
                    stateDb = value
                    println("Received ${value[0]}")
                }
            } catch (e: Exception) {
                println("The flow has thrown an exception: $e")
            }
        }
    }
}
