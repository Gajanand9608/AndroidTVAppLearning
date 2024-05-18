package com.example.tv3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tv3.api.TmdbRepo

class DetailViewModelFactory(val repo : TmdbRepo, val movieId : Int) :  ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repo = repo, id = movieId) as T
    }
}