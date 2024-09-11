package com.apirequest.movapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apirequest.movapp.core.Constans
import com.apirequest.movapp.models.MovieModel
import com.apirequest.movapp.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel: ViewModel() {
    private var movieList = MutableLiveData<List<MovieModel>>()
    val moviesList: LiveData<List<MovieModel>> = movieList

    fun getAllMovies(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getAllMovies(Constans.API_KEY)
            withContext(Dispatchers.Main){
                movieList.value = response.body()!!.results.sortedBy { it.title }
            }
        }
    }

    fun getPopular(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getAllMovies(Constans.API_KEY)
            withContext(Dispatchers.Main){
                movieList.value = response.body()!!.results.sortedBy { (it.popularity).toFloat() }
            }
        }
    }

    fun getTopRated(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getAllMovies(Constans.API_KEY)
            withContext(Dispatchers.Main){
                movieList.value = response.body()!!.results.sortedByDescending { (it.rating).toFloat() }
            }
        }
    }

    fun getUpcoming(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getAllMovies(Constans.API_KEY)
            withContext(Dispatchers.Main){
                movieList.value = response.body()!!.results.sortedBy { it.title }
            }
        }
    }

}