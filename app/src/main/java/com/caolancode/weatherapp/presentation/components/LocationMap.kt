package com.caolancode.weatherapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.caolancode.weatherapp.R
import com.caolancode.weatherapp.domain.WeatherViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationMap(
    modifier: Modifier = Modifier,
    weatherViewModel: WeatherViewModel
) {
    val location by weatherViewModel.location.collectAsState()
    val latitude by weatherViewModel.latitude.collectAsState()
    val longitude by weatherViewModel.longitude.collectAsState()
    val mapHeight = dimensionResource(id = R.dimen.map_height)
    val locationLatLon = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(locationLatLon, 10f)
    }

    Box{
        GoogleMap(
            modifier = modifier
                .fillMaxWidth()
                .height(mapHeight),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = locationLatLon),
                title = location,
                snippet = "Marker in $location"
            )
        }
    }
}