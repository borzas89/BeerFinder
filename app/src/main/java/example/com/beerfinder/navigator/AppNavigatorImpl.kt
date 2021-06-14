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


    override fun navigateToMarker(markerId: Long) {
        navController.navigate(
            MarkerListFragmentDirections
                .actionMarkerListFragmentToMapFragment(markerId)
        )
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}