package example.com.beerfinder.ui.map

import example.com.beerfinder.model.MarkerPresentationModel

sealed class MapViewState

data class MarkerListReady(val markerList: List<MarkerPresentationModel>) : MapViewState()

object Loading : MapViewState()

data class Error(
    val message: String
) : MapViewState()
