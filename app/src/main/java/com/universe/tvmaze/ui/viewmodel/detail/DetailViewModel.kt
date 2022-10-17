package com.universe.tvmaze.ui.viewmodel.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universe.tvmaze.domain.model.DetailCastDomain
import com.universe.tvmaze.domain.model.DetailCastItemDomain
import com.universe.tvmaze.domain.model.DetailDomain
import com.universe.tvmaze.domain.use_cases.GetDetailCast
import com.universe.tvmaze.domain.use_cases.GetDetailMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailMovie: GetDetailMovie,
    private val getDetailCast: GetDetailCast
) : ViewModel() {
    val isLoadingDetail = MutableLiveData<Boolean>()
    val isLoadingCast = MutableLiveData<Boolean>()
    val movieDetail = MutableLiveData<DetailDomain?>()
    val movieCast = MutableLiveData<MutableList<DetailCastItemDomain>?>()

    fun onCreate(idMovie: Long){
        viewModelScope.launch {
            isLoadingDetail.postValue(true)
            val result = getDetailMovie(idMovie)
            if(result != null){
                movieDetail.postValue(result)
            }
            isLoadingDetail.postValue(false)

        }
    }
    fun getCast(idMovie: Long){
        viewModelScope.launch {
            isLoadingCast.postValue(true)
            val result = getDetailCast(idMovie)
            if(result != null){
                movieCast.postValue(result)
            }
            isLoadingCast.postValue(false)
        }
    }
}