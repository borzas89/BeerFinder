package example.com.beerfinder.ui.list

import example.com.beerfinder.model.MarkerPresentationModel

sealed class MarkerListViewState

data class MarkerListReady(val markerListings: List<MarkerPresentationModel>) : MarkerListViewState()

object Loading : MarkerListViewState()

data class Error(
    val message: String
) : MarkerListViewState()
