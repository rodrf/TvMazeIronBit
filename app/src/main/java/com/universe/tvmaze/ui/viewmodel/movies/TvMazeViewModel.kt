package com.universe.tvmaze.ui.viewmodel.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universe.tvmaze.domain.model.ScheduleItemDomain
import com.universe.tvmaze.domain.model.SearchItemDomain
import com.universe.tvmaze.domain.use_cases.GetDetailCast
import com.universe.tvmaze.domain.use_cases.GetDetailMovie
import com.universe.tvmaze.domain.use_cases.GetMoviesByDate
import com.universe.tvmaze.domain.use_cases.GetMoviesByParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

/**
 * TODO
 *
 * @property getMoviesByDate use case to get movies by date
 * @property getMoviesByParam use case to get movies by param
 */
@HiltViewModel
class TvMazeViewModel @Inject constructor(
    private val getMoviesByDate: GetMoviesByDate,
    private val getMoviesByParam: GetMoviesByParam
): ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val moviesByDate = MutableLiveData<MutableList<ScheduleItemDomain>>()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val localDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
            val result = getMoviesByDate(localDate)
            if(result != null && result.size>0){
                moviesByDate.postValue(result)
            }
            isLoading.postValue(false)
        }
    }
    fun searchByParam(param: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMoviesByParam(param)
            if(result != null && result.size>0){
                moviesByDate.postValue(result)
            }
            isLoading.postValue(false)
        }
    }

}