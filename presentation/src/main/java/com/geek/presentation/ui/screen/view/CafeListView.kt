package com.geek.presentation.ui.screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.geek.domain.model.Cafe

@Composable
fun CafeListScreen(cafes: List<Cafe>) {
    LazyColumn {
        items(cafes) { cafe ->
            Column {
                Text(text = cafe.name)
                Text(text = cafe.address)
            }
        }
    }
}