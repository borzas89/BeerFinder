package example.com.beerfinder.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coordinates (
    @Json(name = "latitude")
    var latitude: Double?,
    @Json(name = "longitude")
    var longitude: Double?
)