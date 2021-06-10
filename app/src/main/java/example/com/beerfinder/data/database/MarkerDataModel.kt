package example.com.beerfinder.data.database

import androidx.room.Entity


@Entity(
        tableName = "Marker"
)
data class MarkerDataModel (
        val name: String,
        val city: String,
        val address: String,
        val urlToImage: String?,
        val description: String?,
        val latitude: Double,
        val longitude: Double
) : BaseDataModel()
