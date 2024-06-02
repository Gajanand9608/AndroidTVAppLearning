package com.example.tv3.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tv3.activities.constants.Constant
import com.example.tv3.model_new.VideoData
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _videosState : MutableState<List<VideoData?>?> = mutableStateOf(null)
    val videoState : State<List<VideoData?>?> = _videosState

    private var _focusImageState : MutableState<String?> = mutableStateOf(null)
    val focusImageState : State<String?> = _focusImageState


    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase(){
        val database = Firebase.database(Constant.database_url)
        val myRef = database.getReference(Constant.reference)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot != null && dataSnapshot.value != null){
                    val data = dataSnapshot.value as Map<String, Any>
                    val videos = data.map {entry ->
                        VideoData.fromMap(entry.value as Map<String, Any>)
                    }
                    _videosState.value = videos
                }else{
                    _videosState.value = emptyList()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        myRef.addValueEventListener(postListener)
    }

    fun setFocusImageState(url : String?){
        _focusImageState.value = url
    }
}