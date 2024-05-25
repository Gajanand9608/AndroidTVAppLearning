package com.example.tv3.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tv3.model2.TvDataModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val imageList : List<String> = emptyList()
    private var _focusImageState : MutableState<String?> = mutableStateOf(null)
    val focusImageState : State<String?> = _focusImageState


    fun setFocusImageState(url : String?){
        _focusImageState.value = url
    }
}