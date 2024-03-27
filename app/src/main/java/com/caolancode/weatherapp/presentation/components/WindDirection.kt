package com.caolancode.weatherapp.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WindDirection(degrees: Float, color: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val adjustedDegrees = (degrees + 180) % 360
        val radius = size.minDimension / 2
        val centerX = size.width / 2
        val centerY = size.height / 2

        val radians = Math.toRadians(adjustedDegrees.toDouble())

        // Calculate arrow points
        val tip = Offset(centerX + 0.5f * radius * sin(radians).toFloat(), centerY - 0.5f * radius * cos(radians).toFloat())
        val left = Offset(centerX + 0.45f * radius * sin(radians + 5 * PI / 6).toFloat(), centerY - 0.45f * radius * cos(radians + 5 * PI / 6).toFloat())
        val right = Offset(centerX + 0.45f * radius * sin(radians - 5 * PI / 6).toFloat(), centerY - 0.45f * radius * cos(radians - 5 * PI / 6).toFloat())

        // Draw arrow
        drawLine(
            color = color,
            start = tip,
            end = left,
            strokeWidth = 10f
        )
        drawLine(
            color = color,
            start = tip,
            end = right,
            strokeWidth = 10f
        )
    }
}