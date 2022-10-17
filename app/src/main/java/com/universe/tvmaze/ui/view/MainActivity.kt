package com.universe.tvmaze.ui.view

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.universe.tvmaze.R
import com.universe.tvmaze.core.helpers.ActivityHelper
import com.universe.tvmaze.databinding.ActivityMainBinding
import com.universe.tvmaze.ui.view.adapter.ITvMazeOnClick
import com.universe.tvmaze.ui.view.adapter.TvMazeAdapter
import com.universe.tvmaze.ui.viewmodel.movies.TvMazeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : ActivityHelper(), ITvMazeOnClick, OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private var isFromDate = true

    private val tvMazeViewModel: TvMazeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(resources.getBoolean(R.bool.portrait_only)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        binding.btnSearch.setOnClickListener(this)

        binding.rvMovies.layoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)
        val localDate = SimpleDateFormat(getString(R.string.date_format_search)).format(Date())

        binding.etDateNow.hint = localDate
        tvMazeViewModel.onCreate()
        tvMazeViewModel.moviesByDate.observe(this) { scheduleItem ->
            binding.rvMovies.adapter = TvMazeAdapter(scheduleItem,this,isFromDate )
            binding.rvMovies.adapter?.notifyDataSetChanged()
            binding.rvMovies.scheduleLayoutAnimation()
        }
        tvMazeViewModel.isLoading.observe(this) {isLoading ->
            if(isLoading)
                this.showLoader(getString(R.string.loading))
            else
                this.hideLoader()
        }
    }


    override fun onClickMovie(id: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("idMovie", id.toLong())
        startActivity(intent)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btnSearch.id->{
                if(binding.etDateNow.text.toString().isNullOrEmpty()){
                    tvMazeViewModel.onCreate()
                    isFromDate = true
                }else{
                    isFromDate = false
                    tvMazeViewModel.searchByParam(binding.etDateNow.text.toString())
                }
            }
        }
    }
}