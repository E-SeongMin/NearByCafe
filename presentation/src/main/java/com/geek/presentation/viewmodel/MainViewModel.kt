package com.geek.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geek.domain.model.Cafe
import com.geek.domain.usecase.GetNearbyCafesUseCase
import com.geek.presentation.util.log.CustomLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNearby: GetNearbyCafesUseCase
) : ViewModel() {
    private val _cafeList = MutableStateFlow<List<Cafe>>(emptyList())
    val cafeList: StateFlow<List<Cafe>> = _cafeList

    fun loadCafes(lat: Double, lon: Double) {
        CustomLog.d("loadCafes lat :${lat}, lon : ${lon}")
        viewModelScope.launch {
            _cafeList.value = getNearby(lat, lon)
        }
    }
}