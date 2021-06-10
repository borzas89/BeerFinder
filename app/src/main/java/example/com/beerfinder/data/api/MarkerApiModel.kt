package example.com.beerfinder.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import example.com.beerfinder.data.api.BaseApiModel
import example.com.beerfinder.data.api.Coordinates

@JsonClass(generateAdapter = true)
data class MarkerApiModel(
    @Json(name = "name")
    var name: String,
    @Json(name = "city")
    var city: String,
    @Json(name = "address")
    var address: String,
    @Json(name = "image")
    var image: String?,
    @Json(name = "description")
    var description: String,
    @Json(name = "coordinates")
    var coordinates: Coordinates?
) : BaseApiModel