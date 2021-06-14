package example.com.beerfinder.ui.map

import android.Manifest
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import example.com.beerfinder.R
import example.com.beerfinder.databinding.FragmentMapBinding
import example.com.beerfinder.extension.onClick
import example.com.beerfinder.model.MarkerPresentationModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import example.com.beerfinder.navigator.AppNavigator
import kotlinx.coroutines.cancel
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment: Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private var map: GoogleMap? = null
    private var lastCameraPosition: CameraPosition? = null

    private val viewModel: MapViewModel by viewModels()
    private val args: MapFragmentArgs by navArgs()

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
       val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            googleMap -> map = googleMap
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
                viewState.observe(viewLifecycleOwner, ::render)

                if(args.headerClicked){
                    loadMarkersByCity(args.markerCity)
                } else{
                    loadMarker(args.markerId)
                }

            }

    }

    private fun createDefaultMapOptions(): GoogleMapOptions {
            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(HUNGARY_LATITUDE, HUNGARY_LONGITUDE))
                .zoom(INITIAL_ZOOM_LEVEL)
                .build()

            return GoogleMapOptions()
                .camera(cameraPosition)
        }



    private fun addingSingleMarker(markerPresentationModel: MarkerPresentationModel){

        val markerOptions = MarkerOptions()
        val latLng = LatLng(markerPresentationModel.latitude,
            markerPresentationModel.longitude)

        markerOptions.position(latLng)
        markerOptions.title(markerPresentationModel.name)

        markerOptions.title(markerPresentationModel.name)

        map!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        map!!.addMarker(markerOptions)

        animateCameraToCity(markerPresentationModel)

    }

    private fun animateCameraToCity(markerPresentationModel: MarkerPresentationModel){
        val latLng = LatLng(markerPresentationModel.latitude,
            markerPresentationModel.longitude)
        map!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12.0f))

    }

   fun render(viewState: MapViewState) {
        when (viewState) {
            is Loading -> {
                binding.loadingProgressBar.visibility = View.VISIBLE

            }

            is MarkerListReady -> {
                binding.loadingProgressBar.visibility = View.GONE
                viewState.markerList.forEach { marker: MarkerPresentationModel->
                    addingSingleMarker(marker)
                }

            }

            is Error -> {
                binding.loadingProgressBar.visibility = View.GONE
                Toast.makeText(context,"Error occured...",Toast.LENGTH_SHORT).show()

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

        companion object {
            private const val MAP_FRAGMENT_TAG = "MapFragment"
            private const val GPS_REQUEST = 1
            private const val DEFAULT_ZOOM_LEVEL = 15f
            private const val CAMERA_MOVE_DELAY = 50L
            private const val HUNGARY_LATITUDE = 46.6804973
            private const val HUNGARY_LONGITUDE = 20.1136325
            private const val INITIAL_ZOOM_LEVEL = 6f

            private const val MARKER_ID = "MARKER_ID"
            private const val MARKER_CITY = "MARKER_CITY"
            private const val HEADER_CLICKED = "HEADER_CLICKED"

            private var markerId: Long = 0
            private var markerCity: String = ""
            private var headerClicked: Boolean = false

        }
}