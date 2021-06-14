package example.com.beerfinder.navigator

interface AppNavigator {

    fun navigateToMarker(markerId: Long)
    fun popBackStack()
}