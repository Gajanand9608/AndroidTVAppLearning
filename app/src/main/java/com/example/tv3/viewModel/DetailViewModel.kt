package com.example.tv3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tv3.api.ResponseState
import com.example.tv3.api.TmdbRepo
import com.example.tv3.model.DetailResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repo: TmdbRepo,
    id: Int
) : ViewModel() {

    private val _movieDetailLiveData : MutableLiveData<ResponseState<DetailResponseModel>> = MutableLiveData(null)
    val movieDetailLiveData : LiveData<ResponseState<DetailResponseModel>> = _movieDetailLiveData
    init {
        getMovieDetailById(id = id)
    }


    private fun getMovieDetailById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _movieDetailLiveData.postValue(ResponseState.Loading())
            try {
                val response = repo.getMovieDetails(id)
                if(response.isSuccessful){
                    if(response.body() != null){
                        _movieDetailLiveData.postValue(ResponseState.Success(response.body()))
                    }else{
                        _movieDetailLiveData.postValue(ResponseState.Error(response.errorBody().toString()))
                    }
                }else{
                    _movieDetailLiveData.postValue(ResponseState.Error("Something went wrong"))
                }
            }catch (e : Exception){
                _movieDetailLiveData.postValue(ResponseState.Error(e.message.toString()))
            }
        }
    }
}