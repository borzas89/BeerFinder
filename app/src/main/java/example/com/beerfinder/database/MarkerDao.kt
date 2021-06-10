package example.com.beerfinder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import example.com.beerfinder.data.database.MarkerDataModel

@Dao
interface MarkerDao {

    @Query("SELECT id, name, city, address, urlToImage, description, latitude, longitude FROM Marker ORDER BY city DESC")
    suspend fun getMarkers(): List<MarkerDataModel>

    @Query("SELECT id, name, city, address, urlToImage, description, latitude, longitude FROM Marker WHERE city = :markerCity")
    suspend fun getMarkersByCity(markerCity: String): List<MarkerDataModel>

    @Query("SELECT id, name, city, address, urlToImage, description, latitude, longitude FROM Marker WHERE id = :markerId")
    suspend fun getMarkersById(markerId: Long): List<MarkerDataModel>

    @Query("SELECT id, name, city, address, urlToImage, description, latitude, longitude FROM Marker WHERE id = :markerId")
    suspend fun getMarker(markerId: Long): MarkerDataModel?

    @Insert
    suspend fun insertMarkers(markers: List<MarkerDataModel>)

    @Query("DELETE FROM Marker")
    suspend fun clearMarkers()
}