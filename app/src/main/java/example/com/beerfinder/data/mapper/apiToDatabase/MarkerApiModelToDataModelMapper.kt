package example.com.beerfinder.data.mapper.apiToDatabase

import example.com.beerfinder.data.api.MarkerApiModel
import example.com.beerfinder.data.database.MarkerDataModel
import javax.inject.Inject

class MarkerApiModelToDataModelMapper @Inject constructor() :
    ApiModelToDataModelMapper<MarkerApiModel, MarkerDataModel>() {

    override fun map(model: MarkerApiModel): MarkerDataModel =
        MarkerDataModel(
            name = model.name,
            city = model.city,
            address = model.address,
            urlToImage = model.image,
            description = model.description,
            latitude = model.coordinates!!.latitude!!,
            longitude = model.coordinates!!.longitude!!

        )
}
