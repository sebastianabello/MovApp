package com.apirequest.movapp.network.response

import com.apirequest.movapp.models.MovieModel
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    var results: List<MovieModel>
)
