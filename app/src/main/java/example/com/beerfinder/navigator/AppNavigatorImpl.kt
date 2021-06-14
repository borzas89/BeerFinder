package example.com.beerfinder.navigator

import android.app.Activity
import androidx.navigation.Navigation
import example.com.beerfinder.R
import example.com.beerfinder.ui.list.MarkerListFragment
import example.com.beerfinder.ui.list.MarkerListFragmentDirections
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    private val activity: Activity
) : AppNavigator {

    private val navController by lazy {
        Navigation.findNavController(activity, R.id.fragmentNavHost)
    }

    override fun navigateToMarker(headerClicked: Boolean,markerId: Long, markerCity: String) {
        navController.navigate(
            MarkerListFragmentDirections
                .actionMarkerListFragmentToMapFragment(headerClicked, markerId, markerCity)
        )
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}