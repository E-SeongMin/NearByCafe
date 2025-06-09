package com.geek.presentation.ui.screen

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.geek.presentation.ui.screen.view.CafeListScreen
import com.geek.presentation.ui.theme.AppTheme
import com.geek.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) loadLocation()
        }.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        setContent {
            AppTheme {
                ContentScreen()
            }
        }
    }

    @Composable
    private fun ContentScreen(viewModel: MainViewModel = hiltViewModel()) {

        val cafeList by viewModel.cafeList.collectAsState()

        CafeListScreen(cafeList)

        LaunchedEffect(Unit) {
            viewModel.loadCafes(37.5665, 126.9780)
        }
    }

    private fun loadLocation() {
        // FusedLocationProviderClient 등으로 실제 위치 획득 후 vm.loadCafes 호출
    }
}