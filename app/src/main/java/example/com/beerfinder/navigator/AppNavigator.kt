package example.com.beerfinder.navigator

interface AppNavigator {

    fun navigateToMarker(headerClicked: Boolean,
                         markerId: Long, markerCity: String)
    fun popBackStack()
}