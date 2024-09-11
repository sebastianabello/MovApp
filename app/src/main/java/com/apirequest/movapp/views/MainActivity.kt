package com.apirequest.movapp.views

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.apirequest.movapp.R
import com.apirequest.movapp.databinding.ActivityMainBinding
import com.apirequest.movapp.viewmodels.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapterMovies: AdapterMovies
    private lateinit var btnGetAll: Button
    private lateinit var btnGetPopular: Button
    private lateinit var btnGetTopRated: Button
    private lateinit var btnGetUpcoming: Button
    private lateinit var tvCategory: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnGetAll = findViewById(R.id.btnGetAll)
        btnGetPopular = findViewById(R.id.btnGetPopular)
        btnGetTopRated = findViewById(R.id.btnGetTopRated)
        btnGetUpcoming = findViewById(R.id.btnGetUpcoming)
        tvCategory = findViewById(R.id.tvCategory)

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        setupRecyclerView()

        tvCategory.text = "PREMIERES"
        viewModel.getAllMovies()

        viewModel.moviesList.observe(this) {
            adapterMovies.moviesList = it
            adapterMovies.notifyDataSetChanged()
        }

        btnGetAll.setOnClickListener {
            tvCategory.text = "PREMIERES"
            viewModel.getAllMovies()
        }

        btnGetPopular.setOnClickListener {
            tvCategory.text = "POPULAR"
            viewModel.getPopular()
        }

        btnGetTopRated.setOnClickListener {
            tvCategory.text = "TOP RATED"
            viewModel.getTopRated()
        }

        btnGetUpcoming.setOnClickListener {
            tvCategory.text = "UPCOMING"
            viewModel.getUpcoming()
        }

    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvMovies.layoutManager = layoutManager
        adapterMovies = AdapterMovies(this, arrayListOf())
        binding.rvMovies.adapter = adapterMovies
    }
}