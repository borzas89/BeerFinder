package example.com.beerfinder.model

data class MarkerPresentationModel(
    val id: Long,
    val name: String,
    val city: String,
    val address: String,
    val urlToImage: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
) : BasePresentationModel
