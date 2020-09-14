package com.example.deggerhiltplayground.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.deggerhiltplayground.Model.Blog
import com.example.deggerhiltplayground.Util.DataState
import com.example.deggerhiltplayground.repository.MainRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val scavedStateHandle: SavedStateHandle
):ViewModel(){

    private val _dataState:MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState : LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope .launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvent->{
                    mainRepository.getBlog()
                        .onEach {dataState ->
                            _dataState.value=dataState

                        }
                        .launchIn(
                            viewModelScope
                        )
                }
                is MainStateEvent.None->{
                    //who care
                }
            }
        }
    }
}

sealed class MainStateEvent{
    object GetBlogEvent:MainStateEvent()

    object None:MainStateEvent()
}