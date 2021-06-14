package example.com.beerfinder.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import example.com.beerfinder.data.api.MarkerApiModel
import example.com.beerfinder.data.mapper.apiToDatabase.MarkerApiModelToDataModelMapper
import example.com.beerfinder.data.mapper.databaseToPresentation.MarkerDataModelToPresentationModelMapper
import example.com.beerfinder.database.MarkerDao
import example.com.beerfinder.model.MarkerPresentationModel
import example.com.beerfinder.network.MarkersService
import kotlinx.coroutines.launch

import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MarkerListViewModel
@Inject constructor(
    private val markersApi: MarkersService,
    private val markerDao: MarkerDao,
    private val markerApiModelToDataModelMapper: MarkerApiModelToDataModelMapper,
    private val markerDataModelToPresentationModelMapper: MarkerDataModelToPresentationModelMapper
) : ViewModel() {

    private val _viewState = MutableLiveData<MarkerListViewState>()
    val viewState: LiveData<MarkerListViewState>
        get() = _viewState

    init {
        _viewState.value = Loading
    }

    fun loadList() {
        viewModelScope.launch {
            _viewState.value = Loading

            try {
                val markersResponse =
                    markersApi.getMarkers().await()


                if (markersResponse.isNotEmpty()) {

                    _viewState.value = MarkerListReady(saveAndReturnMarkerList(markersResponse))


                } else {
                    _viewState.value =
                        Error("Bad response: HTTP")
                }
            } catch (e: Exception) {
                _viewState.value = Error("Unknown error: ${e.message}")
            }
        }
    }

    private suspend fun saveAndReturnMarkerList(
        markers: List<MarkerApiModel>
    ): List<MarkerPresentationModel> {
        with(markerDao) {
            clearMarkers()
            insertMarkers(markerApiModelToDataModelMapper.map(markers))

          return getMarkers().map{ markerDataModel ->
                markerDataModelToPresentationModelMapper.map(markerDataModel)
            }
        }
    }

}