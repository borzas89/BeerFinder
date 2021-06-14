package example.com.beerfinder.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import example.com.beerfinder.data.mapper.databaseToPresentation.MarkerDataModelToPresentationModelMapper
import example.com.beerfinder.database.MarkerDao
import example.com.beerfinder.ui.list.MarkerListViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel
@Inject constructor(
        private val markerDao: MarkerDao,
        private val dataModelToPresentationModelMapper: MarkerDataModelToPresentationModelMapper
) : ViewModel() {

    private val _viewState = MutableLiveData<MapViewState>()
    val viewState: LiveData<MapViewState>
        get() = _viewState

    init {
        _viewState.value = Loading
    }


    fun loadMarker(markerId: Long) {
        viewModelScope.launch {
            _viewState.value = Loading

            val marker = markerDao.getMarkersById(markerId)

            _viewState.value = MarkerListReady(
                    dataModelToPresentationModelMapper
                            .map(marker)
            )
        }
    }

    fun loadMarkersByCity(markerCity: String) {
        viewModelScope.launch {
            _viewState.value = Loading

            _viewState.value = MarkerListReady(
                    markerDao.getMarkersByCity(markerCity).map {
                        dataModelToPresentationModelMapper.map(it)
                    }
            )
        }
    }


}
