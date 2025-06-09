package com.geek.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = androidx.compose.material3.darkColorScheme(
            primary   = Primary,
            secondary = Secondary
        ),
        typography = AppTypography,
        shapes     = AppShapes,
        content    = content
    )
}